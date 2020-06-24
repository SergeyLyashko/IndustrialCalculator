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
 * �������� ���� ��� �����-���� ���� �� ��������
 * �� ���� ������
 * @author Sergei Lyashko
 */
public class Menu {////////////////////////////////TODO ���������� � MenuModel
    
    // Sql ������ ������� �����������(��������)
    private static final String SQL_QUERY_PROFILES = 
        "select ProfileName from Profiles";
    
    // Sql ������ ������� ����� ��������, � ����������� �� ���������� ����������
    private static final String SQL_QUERY_TYPES = 
        "select ProfileTypeName from Profiles, ProfileTypes "
        + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
        + "Profiles.ProfileName = ?";
    
    // Sql ������ ������� ������� ��������, � ����������� �� ���������� ���������� � ���� �������
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
    
    private final String assortmentHeader = "��� ����������";
    private final String typeHeader = "��� �������";
    private final String numberHeader = "� �������";
    
    private String assortment;
    private String type;
    
    /**
     * �������� ������ ���� ��� ������� ������ ����������� ����
     * @return ������ �������� ����
     */
    public MenuModel createModel(){
        return modelCreator(assortmentHeader, profileName, SQL_QUERY_PROFILES);
    }
    
    /**
     * �������� ������ ���� ����� �������� ������������ ����������
     * @param assortment ������������ ����������
     * @return ������ ���� ����� ��������
     */
    public MenuModel createModel(String assortment){
        this.assortment = assortment;
        return modelCreator(typeHeader, profileTypeName, SQL_QUERY_TYPES);
    }
    
    /**
     * �������� ������ ���� ������� �������� ����������� ���������� �
     * ���� �������
     * @param assortment ������������ ����������
     * @param type ������������ ���� �������
     * @return ������ ���� ������� ��������
     */
    public MenuModel createModel(String assortment, String type){
        this.assortment = assortment;
        this.type = type;
        return modelCreator(numberHeader, profileNumberName, SQL_QUERY_NUMBERS);
    }
    
    /**
     * ���������� ���������� ������ � ����
     * @param menuBox �����-���� � ���������� ����
     * @return ������ ���� � ��������� ������� ����
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
            // �������� �������� ������� ����������
            if(assortment != null){
                preparedStatement.setString(1, assortment);
            }
            if(type != null){
                preparedStatement.setString(2, type);
            }            
            // ����������� ������������� ���������
            ResultSet resultSet = preparedStatement.executeQuery();
            // ���������� ����� � ����
            while(resultSet.next()){
                menu.add(resultSet.getString(queryString));
            }
            // ��������
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException ex){
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new MenuModel(menu);
    }
        
    // �������� ����������
    private void closeQueryBD(Connection connection, PreparedStatement ps, ResultSet resultSet){
        try{
            connection.close();
            ps.close();
            resultSet.close();
        } catch (SQLException ex){
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ����������� � ��
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
