package database;

import view.ReceivableMenu;

import java.util.List;

class ReceivableMenuImpl implements ReceivableMenu {

    private final MenuList menuList;

    ReceivableMenuImpl(Data data) {
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
