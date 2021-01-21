package database;

import view.DataBaseMenuReceiver;

import java.sql.SQLException;
import java.util.List;

class DataBaseMenuReceiverImpl implements DataBaseMenuReceiver {

    private final DetailParametersListCreator detailParametersListCreator;

    DataBaseMenuReceiverImpl(Executor executor) {
        detailParametersListCreator = new DetailParametersListCreator(executor);
    }

    @Override
    public List<String> getAssortmentMenu() throws SQLException {
        return detailParametersListCreator.createAssortmentList();
    }

    @Override
    public List<String> getTypeMenu(String assortment) throws SQLException {
        return detailParametersListCreator.createTypeList(assortment);
    }

    @Override
    public List<String> getNumberMenu(String assortment, String type) throws SQLException {
        return detailParametersListCreator.createNumberList(assortment, type);
    }
}
