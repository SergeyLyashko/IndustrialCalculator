package view.calculator.menuboxes;

import view.MenuReceiver;
import view.calculator.MenuSelectable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private final MenuReceiver menuReceiver;
    private final List<String> menuList;
    private int selected;

    public MenuModel(MenuReceiver menuReceiver) {
        this.menuReceiver = menuReceiver;
        menuList = new ArrayList<>();
    }

    void createMenu(MenuSelectable source){
        String header = source.getHeaderMenu();
        menuList.add(header);
        List<String> receiveMenu = source.receiveMenu(menuReceiver);
        if(receiveMenu != null){
            menuList.addAll(receiveMenu);
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
