/*
 * Copyright 2019 Sergei Lyashko. Contacts: <9lLLLepuLLa@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcdatabase;

import calcmassview.base.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс работы с Базой данных
 * @author Sergei Lyashko
 */
public class DataBase implements DataBaseInterface {
    
    // Sql запрос таблицы сортаментов(профилей)
    private static final String SQL_QUERY_PROFILES = 
        "select ProfileName from Profiles";
    
    // Sql запрос таблицы типов профилей сортамента
    private static final String SQL_QUERY_TYPES = 
        "select ProfileTypeName from Profiles, ProfileTypes "
        + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
        + "Profiles.ProfileName = ?";
    
    // Sql запрос таблицы номеров профилей определенного типа
    private static final String SQL_QUERY_NUMBERS = 
        "select ProfileNumberName from "
      + "Profiles, ProfileTypes, ProfileNumbers "
      + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
      + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
      + "Profiles.ProfileName = ? and "
      + "ProfileTypes.ProfileTypeName = ?";
    
    // SQL запрос значения из базы данных
    private static final String SQL_QUERY_VALUE = "select AreaCut_Value from "
    + "Profiles, ProfileTypes, ProfileNumbers, NumberValues "
    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
    + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
    + "ProfileNumbers.ProfileNumber_ID = NumberValues.Number_ID and "
    + "Profiles.ProfileName = ? and "
    + "ProfileTypes.ProfileTypeName = ? and "
    + "ProfileNumbers.ProfileNumberName = ?";
    
    private final String numberName = "ProfileNumberName";
    private final String typeName = "ProfileTypeName";
    private final String assortmentName = "ProfileName";
    
    private final String assortmentHeader = "Тип сортамента";
    private final String typeHeader = "Тип профиля";
    private final String numberHeader = "№ профиля";
    
    private final String valueFromDB = "AreaCut_Value";
    
    private String assortment, type, number;
    
    @Override
    public void query(String profile, String type, String number){
        this.assortment = profile;
        this.type = type;
        this.number = number;
    }
    
    @Override
    public ArrayList<String> receiveMenuList(){
        if(type != null){
            return createNumberMenu();
        }else if(assortment != null){
            return createTypeMenu();
        }else{
            return createAssortmentMenu();
        }
    }

    @Override
    public double receiveValue() {
        double result = 0;
        try{
            Connection connect = DataBaseConnection.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(SQL_QUERY_VALUE);        
            // передача значений входных параметров
            preparedStatement.setString(1, assortment);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, number);
            // регистрация возвращаемого параметра
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.getDouble(valueFromDB);
            // закрытие
            close(connect, preparedStatement, resultSet);
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    // закрытие соединений
    private void close(Connection connection, PreparedStatement ps, ResultSet resultSet){
        try{
            connection.close();
            ps.close();
            resultSet.close();
        } catch (SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // создание списка меню сортамента
    private ArrayList<String> createAssortmentMenu(){
        return createMenuList(assortmentHeader, assortmentName, SQL_QUERY_PROFILES);
    }
    
    // создание списка меню типов профиля
    private ArrayList<String> createTypeMenu(){
        return createMenuList(typeHeader, typeName, SQL_QUERY_TYPES);
    }
    
    // создание списка меню номеров профиля
    private ArrayList<String> createNumberMenu(){
        return createMenuList(numberHeader, numberName, SQL_QUERY_NUMBERS);
    }
    
    // создание списка для меню из базы данных
    private ArrayList<String> createMenuList(String menuHeader, String queryString, String sqlQuery){
        ArrayList<String> menuList = new ArrayList<>();
        menuList.add(menuHeader);
        try{
            Connection connection = DataBaseConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            // передача значений входных параметров
            if(assortment != null){
                preparedStatement.setString(1, assortment);
            }
            if(type != null){
                preparedStatement.setString(2, type);
            }            
            // регистрация возвращаемого параметра
            ResultSet resultSet = preparedStatement.executeQuery();
            // добавление строк в меню
            while(resultSet.next()){
                menuList.add(resultSet.getString(queryString));
            }
            // закрытие
            close(connection, preparedStatement, resultSet);
        }catch(SQLException ex){
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menuList;
    }
}