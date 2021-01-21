package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Executor {

    private Connection connection;

    void addConnection(Connector connector) {
        try {
            connection = connector.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void initPreparedStatement(PreparedStatement preparedStatement, int index, String field) throws SQLException {
        preparedStatement.setString(index, field);
    }

    PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }

    ResultSet getResultSet(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }
}
