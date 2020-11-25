package view.model;

import view.controller.MenuSelectable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private final List<String> menuList = new ArrayList<>();
    private int selected;

    public MenuModel(MenuSelectable menuSelectable, List<String> menu){
        String headerMenu = menuSelectable.getHeaderMenu();
        menuList.add(headerMenu);
        if(menu != null){
            menuList.addAll(menu);
        }
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
