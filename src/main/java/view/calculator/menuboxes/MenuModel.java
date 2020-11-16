package view.calculator.menuboxes;

import javax.swing.*;
import java.util.List;

public class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private List<String> menuList;
    private int selected;

    void createModel(List<String> receiveMenu){
        menuList = receiveMenu;
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
