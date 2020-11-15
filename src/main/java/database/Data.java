package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Data {

    private Connection connection;

    void addConnection(Connector connector) {
        try {
            connection = connector.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }

    void initPreparedStatement(PreparedStatement preparedStatement, int index, String field) throws SQLException {
        preparedStatement.setString(index, field);
    }

    List<String> getMenuList(PreparedStatement preparedStatement, String query) throws SQLException {
        List<String> menuList = new ArrayList<>();
        ResultSet resultSet = getResultSet(preparedStatement);
        while(resultSet.next()){
            String elementMenu = resultSet.getString(query);
            menuList.add(elementMenu);
        }
        return menuList;
    }

    private ResultSet getResultSet(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }
}
