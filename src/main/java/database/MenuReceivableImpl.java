package database;

import view.controller.MenuReceivable;

import java.util.List;

class MenuReceivableImpl implements MenuReceivable {

    private final MenuList menuList;

    MenuReceivableImpl(Data data) {
        menuList = new MenuList(data);
    }

    @Override
    public List<String> getAssortmentMenu() {
        return menuList.receiveAssortmentList();
    }

    @Override
    public List<String> getTypeMenu(String assortment) {
        return menuList.receiveTypeList(assortment);
    }

    @Override
    public List<String> getNumberMenu(String type) {
        return menuList.receiveNumberList(type);
    }
}
