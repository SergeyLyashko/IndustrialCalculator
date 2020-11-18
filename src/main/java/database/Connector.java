package database;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Connector {

    private static final String DRIVER_PREFIX = "jdbc:sqlite:";

    /**
     * Выполняет соединение с базой данных,
     * определяя CLASSPATH файла базы данных и используя
     * префикс драйвера соединения
     * @return соединение с БД
     */
    Connection getConnection() throws SQLException {
        URL resource = this.getClass().getResource("data/database/calculator.db");
        String url = DRIVER_PREFIX +resource;
        return DriverManager.getConnection(url);
    }
}
