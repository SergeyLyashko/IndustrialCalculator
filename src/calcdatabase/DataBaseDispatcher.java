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

import java.io.Serializable;
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
public class DataBaseDispatcher implements Serializable, DataBaseMenuReceiver, DataBaseValueReceiver {
    
    private static final long serialVersionUID = 1L;
    
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
    
    private String assortment;
    private String type;
    
    @Override
    public ArrayList<String> getAssortmentMenu(){
        ArrayList<String> menuList = new ArrayList<>();
        menuList.add(assortmentHeader);
        addMenuFromDataBase(menuList, assortmentName, SQL_QUERY_PROFILES);
        return menuList;
    }
    
    @Override
    public ArrayList<String> getNumberMenu(String assortment, String type){
        ArrayList<String> menuList = new ArrayList<>();
        menuList.add(numberHeader);
        if(assortment != null & type != null){
            this.assortment = assortment;
            this.type = type;
            addMenuFromDataBase(menuList, numberName, SQL_QUERY_NUMBERS);
        }
        return menuList;
    }
    
    // создание списка меню типов профиля
    @Override
    public ArrayList<String> getTypeMenu(String assortment){
        ArrayList<String> menuList = new ArrayList<>();
        menuList.add(typeHeader);
        if(assortment != null){
            this.assortment = assortment;
            addMenuFromDataBase(menuList, typeName, SQL_QUERY_TYPES);
        }
        return menuList;
    }
    
    private void addMenuFromDataBase(ArrayList<String> menuList, String queryString, String sqlQuery){
        Connection connection = DataBaseConnection.getConnect();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            // передача значений входных параметров
            if(assortment != null){
                preparedStatement.setString(1, assortment);
            }
            if(type != null){
                preparedStatement.setString(2, type);
            }            
            // test
            //System.out.println("test data base assort: "+assortment+" type: "+type);
            // регистрация возвращаемого параметра
            ResultSet resultSet = preparedStatement.executeQuery();
            // добавление строк в меню
            while(resultSet.next()){
                menuList.add(resultSet.getString(queryString));
            }
            // закрытие
            close(connection, preparedStatement, resultSet);
        }catch(SQLException ex){
            Logger.getLogger(DataBaseDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public double getDataBaseValue(String assortment, String type, String number) {
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
            Logger.getLogger(DataBaseDispatcher.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DataBaseDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}