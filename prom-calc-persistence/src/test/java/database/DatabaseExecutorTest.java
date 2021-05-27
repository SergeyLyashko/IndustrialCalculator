package database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class DatabaseExecutorTest {

    private static final Connection mockConnection = mock(Connection.class);

    @Test
    public void addConnection() throws SQLException, ClassNotFoundException {
        DatabaseConnector mockDatabaseConnector = mock(DatabaseConnector.class);
        when(mockDatabaseConnector.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void executorQuery() throws SQLException {
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement("testQuery")).thenReturn(mockPreparedStatement);

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        String testValue = "test";
        ResultHandler mockResultHandler = mock(ResultHandler.class);
        when(mockResultHandler.handle(mockResultSet)).thenReturn(testValue);
    }

    @Test
    public void connectionClose() throws SQLException {
        doNothing().doThrow(new SQLException()).when(mockConnection).close();
    }
}