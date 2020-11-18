package view.calculator.menuboxes;

import view.calculator.SelectableMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private final List<String> menuList = new ArrayList<>();
    private final SelectableMenu menuListener;
    private int selected;

    public MenuModel(SelectableMenu menuListener) {
        this.menuListener = menuListener;
    }

    public void createModel(String...menuItem){
        String headerMenu = menuListener.getHeaderMenu();
        menuList.add(headerMenu);
        List<String> menu = menuListener.receiveMenu(menuItem);
        if(menu != null){
            menuList.addAll(menu);
        }
        menuListener.setModel(this);
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
