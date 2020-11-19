package view.calculator.menuboxes;

import view.calculator.MenuSelectable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private final List<String> menuList = new ArrayList<>();
    private int selected;

    public MenuModel(MenuSelectable menuListener, String menuItem){
        String headerMenu = menuListener.getHeaderMenu();
        menuList.add(headerMenu);
        List<String> menu = menuListener.receiveMenu(menuItem);
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
