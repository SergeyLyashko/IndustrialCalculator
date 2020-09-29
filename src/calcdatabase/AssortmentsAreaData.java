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

import calcmassview.MenuListReceiver;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import calcmassmodel.AssortmentsAreaReceiver;
import java.util.Collections;

/**
 * Класс работы с Базой данных
 * @author Sergei Lyashko
 */
class AssortmentsAreaData implements Serializable, MenuListReceiver, AssortmentsAreaReceiver {
    
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
    
    private ArrayList<String> menuList;
    
    @Override
    public List<String> getAssortmentMenu(){
        String selectedAssortment = null;
        String selectedType = null;
        menuList = new ArrayList<>();
        menuList.add(assortmentHeader);
        extractMenu(selectedAssortment, selectedType, assortmentName, SQL_QUERY_PROFILES);
        return Collections.unmodifiableList(menuList);
    }
    
    @Override
    public List<String> getTypeMenu(String selectedAssortment){
        String selectedType = null;
        menuList = new ArrayList<>();
        menuList.add(typeHeader);
        if(selectedAssortment != null){
            extractMenu(selectedAssortment, selectedType, typeName, SQL_QUERY_TYPES);
        }
        return Collections.unmodifiableList(menuList);
    }
    
    @Override
    public List<String> getNumberMenu(String selectedAssortment, String selectedType){
        menuList = new ArrayList<>();
        menuList.add(numberHeader);
        if(selectedAssortment != null && selectedType != null){
            extractMenu(selectedAssortment, selectedType, numberName, SQL_QUERY_NUMBERS);
        }        
        return Collections.unmodifiableList(menuList);
    }
    
    private void extractMenu(String assortment, String type, String queryString, String sqlQuery){
        PreparedStatement preparedStatement = getPreparedStatement(sqlQuery);
        ResultSet resultSet = getResultSet(preparedStatement, assortment, type);
        addMenuFromDataBase(resultSet, queryString);
        close(preparedStatement, resultSet);
    }
    
    private ResultSet getResultSet(PreparedStatement preparedStatement, String assortment, String type){
        // test
        System.out.println("test data base assort: "+assortment+" type: "+type);
        ResultSet resultSet = null;
        try {
            if(assortment != null){
                if(type != null){
                    preparedStatement.setString(1, assortment);
                    preparedStatement.setString(2, type);
                }else{
                    preparedStatement.setString(1, assortment);            
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AssortmentsAreaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }
    
    private PreparedStatement getPreparedStatement(String sqlQuery){
        Connection connection = DataBaseConnector.getConnect();
        try {
            return connection.prepareStatement(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(AssortmentsAreaData.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DataBaseConnector.close();
        }
        return null;
    }
    
    private void addMenuFromDataBase(ResultSet resultSet, String queryString){
        try {
            while(resultSet.next()){
                menuList.add(resultSet.getString(queryString));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssortmentsAreaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public double getDetailArea(String assortment, String type, String number) {
        double result = 0;
        try{
            Connection connect = DataBaseConnector.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(SQL_QUERY_VALUE);        
            // передача значений входных параметров
            preparedStatement.setString(1, assortment);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, number);
            // регистрация возвращаемого параметра
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.getDouble(valueFromDB);
            // закрытие
            close(preparedStatement, resultSet);
        }catch(SQLException ex){
            Logger.getLogger(AssortmentsAreaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    // закрытие соединений
    private void close(PreparedStatement ps, ResultSet resultSet){
        try{
            ps.close();
            resultSet.close();
        } catch (SQLException ex){
            Logger.getLogger(AssortmentsAreaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}