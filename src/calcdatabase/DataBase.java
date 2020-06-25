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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SQL запрос в Ѕƒ
 * @author Sergei Lyashko
 */
public class DataBase implements DataBaseInterface {
    
    private static final String SQL_QUERY = "select AreaCut_Value from "
    + "Profiles, ProfileTypes, ProfileNumbers, NumberValues "
    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
    + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
    + "ProfileNumbers.ProfileNumber_ID = NumberValues.Number_ID and "
    + "Profiles.ProfileName = ? and "
    + "ProfileTypes.ProfileTypeName = ? and "
    + "ProfileNumbers.ProfileNumberName = ?";
    
    private final String valueFromDB = "AreaCut_Value";
    private String profile, type, number;
    
    @Override
    public double query(String profile, String type, String number){
        this.profile = profile;
        this.type = type;
        this.number = number;
        return receiveValue();
    }

    private double receiveValue() {
        double result = 0;
        try{
            Connection connect = DataBaseConnection.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(SQL_QUERY);        
            // передача значений входных параметров
            preparedStatement.setString(1, profile);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, number);
            // регистраци€ возвращаемого параметра
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
}