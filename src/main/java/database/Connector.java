package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Connector {

    // драйвер подключения к базе
    private static final String DATA_BASE_URL = "jdbc:sqlite:database\\calculator.db";

    /**
     * соединение с базой данных
     * @return соединение с БД
     */
    Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DATA_BASE_URL);
    }
}
