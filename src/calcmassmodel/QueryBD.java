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
import java.sql.PreparedStatement;
import java.sql.SQLException;

//запрос в БД

/**
 * SQL запрос в БД
 * @author Sergei Lyashko
 */
public class QueryBD {
    
    private Connection connection;
    private static final String SQL_QUERY = "select AreaCut_Value from "
    + "Profiles, ProfileTypes, ProfileNumbers, NumberValues "
    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
    + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
    + "ProfileNumbers.ProfileNumber_ID = NumberValues.Number_ID and "
    + "Profiles.ProfileName = ? and "
    + "ProfileTypes.ProfileTypeName = ? and "
    + "ProfileNumbers.ProfileNumberName = ?";
        
    // получение значения из БД
    public double getAreaCutBD(String profile, String type, String number) {
        double result = 0;
        try{
            connection = ConnectorDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);        
            // передача значений входных параметров
            preparedStatement.setString(1, profile);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, number);
            // регистрация возвращаемого параметра
            result = preparedStatement.getResultSet().getDouble(1);
        }catch(SQLException e){
            System.err.println("Ошибка sql: "+e);
        }
        return result;
    }    
}