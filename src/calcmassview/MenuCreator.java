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

import calcmassview.viewpanelcomponent.MenuBoxModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * �����-��������� ���� ��� ���� ���������� ������� � ������ View
 * @author Sergei Lyashko
 */
public class MenuCreator {
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    // Sql ������ ������� �����������(��������)
    private static final String SQL_QUERY_PROFILES = 
        "select ProfileName from Profiles";
    
    // Sql ������ ������� ����� ��������, � ����������� �� ���������� ����������
    private static final String SQL_QUERY_TYPES = 
        "select ProfileTypeName from Profiles, ProfileTypes "
        + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
        + "Profiles.ProfileName = ?";
    
    // Sql ������ ������� ������� ��������, � ����������� �� ���������� ����������
    // � ���� �������
    private static final String SQL_QUERY_NUMBERS = 
        "select ProfileNumberName from "
      + "Profiles, ProfileTypes, ProfileNumbers "
      + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
      + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
      + "Profiles.ProfileName = ? and "
      + "ProfileTypes.ProfileTypeName = ?";
    
    private static final MenuCreator INSTANCE = new MenuCreator();
    
    /**
     * �������� ��������-������� ����
     * @return singleton
     */
    public static MenuCreator getInstance(){
        return INSTANCE;
    }
    
    /**
     * �������� ������ ���� ��� ������� ������ ����������� ����
     * @return ������ �������� ����
     */
    public MenuBoxModel getModel(){
        return new MenuBoxModel(createProfileBaseMenu());
    }
    
    /**
     * �������� ������ ���� ����� �������� ������������ ����������
     * @param assortment ������������ ����������
     * @return ������ ���� ����� ��������
     */
    public MenuBoxModel getModel(String assortment){
        return new MenuBoxModel(createProfileTypeMenu(assortment));
    }
    
    /**
     * �������� ������ ���� ������� �������� ����������� ���������� �
     * ���� �������
     * @param assortment ������������ ����������
     * @param type ������������ ���� �������
     * @return ������ ���� ������� ��������
     */
    public MenuBoxModel getModel(String assortment, String type){
        return new MenuBoxModel(createProfileNumberMenu(assortment, type));
    }
    
    // �������� ������ ������� �������� ����
    private ArrayList<String> createProfileNumberMenu(String assortment, String type){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("� �������");
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY_NUMBERS);
            // �������� �������� ������� ����������
            preparedStatement.setString(1, assortment);
            preparedStatement.setString(2, type);
            // ����������� ������������� ���������
            resultSet = preparedStatement.executeQuery();
            // ���������� ����� � ����
            while(resultSet.next()){
                menu.add(resultSet.getString("ProfileNumberName"));
            }
            // ��������
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException e){
            System.err.println("������ sql: ");
            e.printStackTrace();
        }
        return menu;
    }
    
    // �������� ������ ����� �������� ����������
    private ArrayList<String> createProfileTypeMenu(String assortment){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("��� �������");
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY_TYPES);
            // �������� �������� ������� ����������
            preparedStatement.setString(1, assortment);
            
            // ����������� ������������� ���������
            resultSet = preparedStatement.executeQuery();
            // ���������� ����� � ����
            while(resultSet.next()){
                menu.add(resultSet.getString("ProfileTypeName"));
            }
            // ��������
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException e){
            System.err.println("������ sql: ");
            e.printStackTrace();
        }
        return menu;
    }
    
    // �������� ������ �����������
    private ArrayList<String> createProfileBaseMenu(){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("��� ����������");
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY_PROFILES);        
            // ����������� ������������� ���������
            resultSet = preparedStatement.executeQuery();
            // ���������� ����� � ����
            while(resultSet.next()){
                menu.add(resultSet.getString("ProfileName"));
            }
            // ��������
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException e){
            System.err.println("������ sql: ");
            e.printStackTrace();
        }
        return menu;
    }
    
    // �������� ����������
    private void closeQueryBD(Connection c, PreparedStatement p, ResultSet r){
        try{
            c.close();
            p.close();
            r.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // ����������� � ��
    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./src/database/calculator.db");
        } catch (SQLException ex) {
            System.err.print("������ ����������� � ��. ");
            ex.printStackTrace();
        }
        return connection;
    }
}
