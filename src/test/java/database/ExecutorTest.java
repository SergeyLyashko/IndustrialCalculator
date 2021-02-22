package database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExecutorTest {

    private static final Connection mockConnection = mock(Connection.class);

    @Test
    public void addConnection() throws SQLException, ClassNotFoundException {
        Connector mockConnector = mock(Connector.class);
        when(mockConnector.getConnection()).thenReturn(mockConnection);
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