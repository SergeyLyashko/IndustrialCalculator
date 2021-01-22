package database;

import view.DataReceiver;

import java.sql.SQLException;
import java.util.List;

public class DataBaseReceiver implements DataReceiver {

    private final DatabaseQuery databaseQuery;

    public DataBaseReceiver(){
        Executor executor = new Executor();
        Connector connector = new Connector();
        executor.addConnection(connector);
        databaseQuery = new DatabaseQuery(executor);
    }

    @Override
    public List<String> getAssortmentMenu() throws SQLException {
        return databaseQuery.createAssortmentList();
    }

    @Override
    public List<String> getTypeMenu(String assortment) throws SQLException {
        return databaseQuery.createTypeList(assortment);
    }

    @Override
    public List<String> getNumberMenu(String assortment, String type) throws SQLException {
        return databaseQuery.createNumberList(assortment, type);
    }

    @Override
    public double getValue(String assortment, String type, String number) throws SQLException {
        return databaseQuery.getValue(assortment, type, number);
    }
}
