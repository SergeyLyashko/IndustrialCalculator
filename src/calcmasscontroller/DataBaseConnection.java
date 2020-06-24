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
package calcmasscontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Подключение к Базе Данных 
 * площадей сечений профилей  
 * @author Sergei Lyashko
 */
public class DataBaseConnection {
    
    private static final String DATA_BASE_NAME = "jdbc:sqlite:calculator.db";
    
    /**
     * подключение к базе данных
     * @return соединение с БД
     */
    public static Connection getConnect(){
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DATA_BASE_NAME);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
