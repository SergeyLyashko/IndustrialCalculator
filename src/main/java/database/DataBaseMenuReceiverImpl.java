package database;

import view.DataBaseMenuReceiver;

import java.sql.SQLException;
import java.util.List;

class DataBaseMenuReceiverImpl implements DataBaseMenuReceiver {

    private final DetailsMenuList detailsMenuList;

    DataBaseMenuReceiverImpl(Data data) {
        detailsMenuList = new DetailsMenuList(data);
    }

    @Override
    public List<String> getAssortmentMenu() throws SQLException {
        return detailsMenuList.receiveAssortmentList();
    }

    @Override
    public List<String> getTypeMenu(String assortment) throws SQLException {
        return detailsMenuList.receiveTypeList(assortment);
    }

    @Override
    public List<String> getNumberMenu(String assortment, String type) throws SQLException {
        return detailsMenuList.receiveNumberList(assortment, type);
    }
}
