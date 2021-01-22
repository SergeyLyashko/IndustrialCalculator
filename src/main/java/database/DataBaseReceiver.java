package database;

import view.DataReceiver;

import java.sql.SQLException;
import java.util.List;

public class DataBaseReceiver implements DataReceiver {

    private final DetailsDAO detailsDAO;

    public DataBaseReceiver(){
        this.detailsDAO = new DetailsDAO(new Connector());
    }

    @Override
    public List<String> getAssortmentMenu() throws SQLException {
        return detailsDAO.createAssortmentList();
    }

    @Override
    public List<String> getTypeMenu(String assortment) throws SQLException {
        return detailsDAO.createTypeList(assortment);
    }

    @Override
    public List<String> getNumberMenu(String assortment, String type) throws SQLException {
        return detailsDAO.createNumberList(assortment, type);
    }

    @Override
    public double getValue(String assortment, String type, String number) throws SQLException {
        return detailsDAO.getValue(assortment, type, number);
    }

    @Override
    public void winCloseUpdate() {
        detailsDAO.connectionClose();
    }
}
