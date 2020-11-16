package database;

import view.MenuReceiver;

import java.util.List;

class MenuReceiverImpl implements MenuReceiver {

    private final MenuList menuList;

    MenuReceiverImpl(Data data) {
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
