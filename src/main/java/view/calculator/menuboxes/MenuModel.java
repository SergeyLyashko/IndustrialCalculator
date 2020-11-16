package view.calculator.menuboxes;

import view.MenuReceiver;

import javax.swing.*;
import java.util.List;

class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private final MenuReceiver menuReceiver;
    private List<String> menuList;
    private int selected;

    MenuModel(MenuReceiver menuReceiver) {
        this.menuReceiver = menuReceiver;
    }

    public MenuModel createMenuModel(MenuSelectable source){
        menuList = source.receiveMenu(menuReceiver);
        return this;
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
