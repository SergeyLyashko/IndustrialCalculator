package database;

import view.DataBaseMenuReceiver;

import java.util.List;

class DataBaseMenuReceiverImpl implements DataBaseMenuReceiver {

    private final DetailsMenuList detailsMenuList;

    DataBaseMenuReceiverImpl(Data data) {
        detailsMenuList = new DetailsMenuList(data);
    }

    @Override
    public List<String> getAssortmentMenu() {
        return detailsMenuList.receiveAssortmentList();
    }

    @Override
    public List<String> getTypeMenu(String assortment) {
        return detailsMenuList.receiveTypeList(assortment);
    }

    @Override
    public List<String> getNumberMenu(String assortment, String type) {
        return detailsMenuList.receiveNumberList(assortment, type);
    }
}
