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
package calcmassmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL ������ � ��
 * @author Sergei Lyashko
 */
class QueryBD {
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    private static final String SQL_QUERY = "select AreaCut_Value from "
    + "Profiles, ProfileTypes, ProfileNumbers, NumberValues "
    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
    + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
    + "ProfileNumbers.ProfileNumber_ID = NumberValues.Number_ID and "
    + "Profiles.ProfileName = ? and "
    + "ProfileTypes.ProfileTypeName = ? and "
    + "ProfileNumbers.ProfileNumberName = ?";

    /**
     * ������ �������� ������� ������� ������ �� ��
     * @param profile ������������ ������� (���������)
     * @param type ������������ ���� ������
     * @param number ����� ������� ������
     * @return ������� ������� (� ��2)
     */
    public double getAreaCutBD(String profile, String type, String number) {
        double result = 0;
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY);        
            // �������� �������� ������� ����������
            preparedStatement.setString(1, profile);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, number);
            // ����������� ������������� ���������
            resultSet = preparedStatement.executeQuery();
            // test System.out.println(SQL_QUERY);
            result = resultSet.getDouble("AreaCut_Value");
            // ��������
            closeQueryBD(connection, preparedStatement, resultSet);
        }catch(SQLException e){
            System.err.println("������ sql: ");
            e.printStackTrace();
        }
        return result;
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