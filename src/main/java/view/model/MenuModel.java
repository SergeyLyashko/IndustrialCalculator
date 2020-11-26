package view.model;

import view.controller.MenuSelectable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private final List<String> menuList = new ArrayList<>();
    private final MenuSelectable menuSelectable;
    private final List<String> receiveMenuList;
    private int selected;

    MenuModel(MenuSelectable menuSelectable, List<String> receiveMenuList){
        this.menuSelectable = menuSelectable;
        String headerMenu = menuSelectable.getHeaderMenu();
        menuList.add(headerMenu);
        this.receiveMenuList = receiveMenuList;
    }

    void createMenu(){
        if(receiveMenuList != null){
            menuList.addAll(receiveMenuList);
        }
        JComboBox<String> parent = (JComboBox<String>) menuSelectable.getParent();
        parent.setModel(this);
    }

    @Override
    public int getSize() {
        return menuList.size();
    }

    @Override
    public String getElementAt(int index) {
        return menuList.get(index);
    }

    @Override
    public void setSelectedItem(Object item) {
        selected = menuList.indexOf(item);
    }

    @Override
    public Object getSelectedItem() {
        return menuList.get(selected);
    }
}
