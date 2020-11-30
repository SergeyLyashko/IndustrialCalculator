package database;

import view.MenuListReceivable;

import java.util.List;

class MenuListReceivableImpl implements MenuListReceivable {

    private final DetailsMenuList detailsMenuList;

    MenuListReceivableImpl(Data data) {
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
