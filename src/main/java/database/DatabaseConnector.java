package database;

import org.springframework.stereotype.Service;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
class DatabaseConnector {

    private static final String DRIVER_PREFIX = "jdbc:sqlite:";
    private static final String SOURCE = "/data/database/calculator.db";

    /**
     * Выполняет соединение с базой данных,
     * определяя CLASSPATH файла базы данных и используя
     * префикс драйвера соединения
     * @return соединение с БД
     */
    Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        URL resource = this.getClass().getResource(SOURCE);
        String url = DRIVER_PREFIX +resource;
        return DriverManager.getConnection(url);
    }
}
