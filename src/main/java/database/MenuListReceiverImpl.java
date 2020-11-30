package database;

import view.MenuListReceiver;

import java.util.List;

class MenuListReceiverImpl implements MenuListReceiver {

    private final DetailsMenuList detailsMenuList;

    MenuListReceiverImpl(Data data) {
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
    public List<String> getNumberMenu(String type) {
        return detailsMenuList.receiveNumberList(type);
    }
}
