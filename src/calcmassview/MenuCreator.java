/*
 * Copyright 2019 Sergei Lyashko. Contacts: <slyashko@mail.ru>.
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
package calcmassview;

import calcmassview.viewpanel.MenuBoxModel;
import java.io.File;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Класс-создатель меню для меню выпадающих списков в панели View
 * @author Sergei Lyashko
 */
public class MenuCreator {
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    private Statement createStatement;
    
    // Sql запрос таблицы сортаментов(профилей)
    private static final String SQL_QUERY_PROFILES = 
        "select ProfileName from Profiles";
    
    // Sql запрос таблицы типов профилей, в зависимости от выбранного сортамента
    private static final String SQL_QUERY_TYPES = 
        "select ProfileTypeName from Profiles, ProfileTypes "
        + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
        + "Profiles.ProfileName = ?";
    
    // Sql запрос таблицы номеров профилей, в зависимости от выбранного сортамента
    // и типа профиля
    private static final String SQL_QUERY_NUMBERS = 
        "select ProfileNumberName from "
      + "Profiles, ProfileTypes, ProfileNumbers "
      + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
      + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
      + "Profiles.ProfileName = ? and "
      + "ProfileTypes.ProfileTypeName = ?";
    
    /**
     * создание модели меню для базовой панели выпадающего меню
     * @return модель базового меню
     */
    public MenuBoxModel getModel(){
        return new MenuBoxModel(createProfileBaseMenu());
    }
    
    /**
     * Создание модели меню типов профилей запрошенного сортамента
     * @param assortment наименование сортамента
     * @return модель меню типов профилей
     */
    public MenuBoxModel getModel(String assortment){
        return new MenuBoxModel(createProfileTypeMenu(assortment));
    }
    
    /**
     * Создание модели меню номеров профилей запрошенных сортамента и
     * типа профиля
     * @param assortment наименование сортамента
     * @param type наименование типа профиля
     * @return модель меню номеров профилей
     */
    public MenuBoxModel getModel(String assortment, String type){
        return new MenuBoxModel(createProfileNumberMenu(assortment, type));
    }
    
    // Создание списка номеров профилей меню
    private ArrayList<String> createProfileNumberMenu(String assortment, String type){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("№ профиля");
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY_NUMBERS);
            // передача значений входных параметров
            preparedStatement.setString(1, assortment);
            preparedStatement.setString(2, type);
            // регистрация возвращаемого параметра
            resultSet = preparedStatement.executeQuery();
            // добавление строк в меню
            while(resultSet.next()){
                menu.add(resultSet.getString("ProfileNumberName"));
            }
            // закрытие
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException e){
            System.err.println("Ошибка sql: ");
            e.printStackTrace();
        }
        return menu;
    }
    
    // Создание списка типов профилей сортамента
    private ArrayList<String> createProfileTypeMenu(String assortment){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Тип профиля");
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY_TYPES);
            // передача значений входных параметров
            preparedStatement.setString(1, assortment);
            
            // регистрация возвращаемого параметра
            resultSet = preparedStatement.executeQuery();
            // добавление строк в меню
            while(resultSet.next()){
                menu.add(resultSet.getString("ProfileTypeName"));
            }
            // закрытие
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException e){
            System.err.println("Ошибка sql: ");
            e.printStackTrace();
        }
        return menu;
    }
    
    // создание списка сортаментов
    private ArrayList<String> createProfileBaseMenu(){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Тип сортамента");
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY_PROFILES);    ///??????????????????????
            //createStatement = connection.createStatement();
            // регистрация возвращаемого параметра
            resultSet = preparedStatement.executeQuery();
            //resultSet = createStatement.executeQuery(SQL_QUERY_PROFILES);
            // добавление строк в меню
            while(resultSet.next()){
                menu.add(resultSet.getString("ProfileName"));
            }
            // закрытие
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException e){
            System.err.println("Ошибка sql: ");
            e.printStackTrace();
        }
        return menu;
    }
    
    // закрытие соединений
    private void closeQueryBD(Connection c, PreparedStatement p, ResultSet r){
        try{
            c.close();
            p.close();
            r.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // подключение к БД
    private Connection getConnection(){
        //String path = this.getClass().getResource("\\database\\calculator.db").toString();//.getPath();
        //String path = "IndustrialCalculator.jar\\database\\calculator.db";
        //System.out.println("path: "+path);
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:calculator.db");
        } catch (SQLException ex) {
            System.err.print("Ошибка подключения к БД. ");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
