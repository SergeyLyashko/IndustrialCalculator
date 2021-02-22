package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Executor {

    private Connection connection;

    void addConnection(Connector connector) {
        try {
            this.connection = connector.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    <T> T executorQuery(String sqlQuery, ResultHandler<T> resultHandler, String...parametersStatement) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        statementInit(preparedStatement, parametersStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        T value = resultHandler.handle(resultSet);
        resultSet.close();
        preparedStatement.close();
        return value;
    }

    private void statementInit(PreparedStatement preparedStatement, String...parametersStatement) throws SQLException {
        for(int index=1; index<=parametersStatement.length; index++){
            preparedStatement.setString(index, parametersStatement[index-1]);
        }
    }

    /**
     * Close database connection
     */
    void connectionClose() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
