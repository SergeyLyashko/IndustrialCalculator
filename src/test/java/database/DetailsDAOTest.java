package database;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DetailsDAOTest {

    @Test
    public void createAssortmentMenu() throws SQLException {
        List<String> testList = new ArrayList<>();
        DatabaseExecutor mockDatabaseExecutor = mock(DatabaseExecutor.class);
        ResultHandler mockResultHandler = mock(ResultHandler.class);
        when(mockDatabaseExecutor.executorQuery("testQuery", mockResultHandler, "testParameter"))
                .thenReturn(testList);

        when(mockDatabaseExecutor.executorQuery("testQuery", mockResultHandler, "testParameter"))
                .thenThrow(new SQLException());
    }

    @Test
    public void createTypeMenu() throws SQLException {
        List<String> testList = new ArrayList<>();
        DatabaseExecutor mockDatabaseExecutor = mock(DatabaseExecutor.class);
        ResultHandler mockResultHandler = mock(ResultHandler.class);
        when(mockDatabaseExecutor.executorQuery("testQuery", mockResultHandler, "testParameter1", "testParameter2"))
                .thenReturn(testList);
        when(mockDatabaseExecutor.executorQuery("testQuery", mockResultHandler, "testParameter1", "testParameter2"))
                .thenThrow(new SQLException());
    }

    @Test
    public void createNumberMenu() throws SQLException {
        List<String> testList = new ArrayList<>();
        DatabaseExecutor mockDatabaseExecutor = mock(DatabaseExecutor.class);
        ResultHandler mockResultHandler = mock(ResultHandler.class);
        when(mockDatabaseExecutor.executorQuery("testQuery", mockResultHandler,
                "testParameter1", "testParameter2", "testParameter3"))
                .thenReturn(testList);
        when(mockDatabaseExecutor.executorQuery("testQuery", mockResultHandler,
                "testParameter1", "testParameter2", "testParameter3"))
                .thenThrow(new SQLException());
    }

    @Test
    public void testGetValue() throws SQLException {
        DatabaseExecutor mockDatabaseExecutor = mock(DatabaseExecutor.class);
        double testValue = 1.1;
        when(mockDatabaseExecutor.executorQuery("testParameter",
                resultSet -> resultSet.getDouble("testQuery"))).thenReturn(testValue);
        when(mockDatabaseExecutor.executorQuery("testParameter",
                resultSet -> resultSet.getDouble("testQuery"))).thenThrow(new SQLException());
    }

    @Test
    public void winCloseUpdate() {
        DatabaseExecutor mockDatabaseExecutor = mock(DatabaseExecutor.class);
        doNothing().doThrow(new RuntimeException()).when(mockDatabaseExecutor).connectionClose();
    }
}