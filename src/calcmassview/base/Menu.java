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
package calcmassview.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 * Создание меню для комбо-бокс меню из профилей
 * из Базы Данных
 * @author Sergei Lyashko
 */
public class Menu {////////////////////////////////TODO объединить с MenuModel
    
    // Sql запрос таблицы сортаментов(профилей)
    private static final String SQL_QUERY_PROFILES = 
        "select ProfileName from Profiles";
    
    // Sql запрос таблицы типов профилей, в зависимости от выбранного сортамента
    private static final String SQL_QUERY_TYPES = 
        "select ProfileTypeName from Profiles, ProfileTypes "
        + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
        + "Profiles.ProfileName = ?";
    
    // Sql запрос таблицы номеров профилей, в зависимости от выбранного сортамента и типа профиля
    private static final String SQL_QUERY_NUMBERS = 
        "select ProfileNumberName from "
      + "Profiles, ProfileTypes, ProfileNumbers "
      + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
      + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
      + "Profiles.ProfileName = ? and "
      + "ProfileTypes.ProfileTypeName = ?";
    
    private final String profileNumberName = "ProfileNumberName";
    private final String profileTypeName = "ProfileTypeName";
    private final String profileName = "ProfileName";
    
    private final String assortmentHeader = "Тип сортамента";
    private final String typeHeader = "Тип профиля";
    private final String numberHeader = "№ профиля";
    
    private String assortment;
    private String type;
    
    /**
     * создание модели меню для базовой панели выпадающего меню
     * @return модель базового меню
     */
    public MenuModel createModel(){
        return modelCreator(assortmentHeader, profileName, SQL_QUERY_PROFILES);
    }
    
    /**
     * Создание модели меню типов профилей запрошенного сортамента
     * @param assortment наименование сортамента
     * @return модель меню типов профилей
     */
    public MenuModel createModel(String assortment){
        this.assortment = assortment;
        return modelCreator(typeHeader, profileTypeName, SQL_QUERY_TYPES);
    }
    
    /**
     * Создание модели меню номеров профилей запрошенных сортамента и
     * типа профиля
     * @param assortment наименование сортамента
     * @param type наименование типа профиля
     * @return модель меню номеров профилей
     */
    public MenuModel createModel(String assortment, String type){
        this.assortment = assortment;
        this.type = type;
        return modelCreator(numberHeader, profileNumberName, SQL_QUERY_NUMBERS);
    }
    
    /**
     * Добавление заглавного пункта в меню
     * @param menuBox комбо-бокс с выпадающим меню
     * @return модель меню с заглавным пунктом меню
     */
    public MenuModel addHeaderMenuItem(JComboBox<String> menuBox){
        ArrayList<String> menu = new ArrayList<>();
        if(menuBox.getClass().equals(TypeProfileMenu.class)){
            menu.add(typeHeader);
        }
        if(menuBox.getClass().equals(NumberProfileMenu.class)){
            menu.add(numberHeader);
        }        
        return new MenuModel(menu);
    }
    
    private MenuModel modelCreator(String menuHeader, String queryString, String sqlQuery){
        ArrayList<String> menu = new ArrayList<>();
        menu.add(menuHeader);
        try{
            Connection connection = connectToDataBase();
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
                menu.add(resultSet.getString(queryString));
            }
            // закрытие
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException ex){
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new MenuModel(menu);
    }
        
    // закрытие соединений
    private void closeQueryBD(Connection connection, PreparedStatement ps, ResultSet resultSet){
        try{
            connection.close();
            ps.close();
            resultSet.close();
        } catch (SQLException ex){
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // подключение к БД
    private Connection connectToDataBase(){
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:calculator.db");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
