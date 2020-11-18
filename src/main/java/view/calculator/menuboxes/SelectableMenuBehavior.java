package view.calculator.menuboxes;

import view.calculator.SelectableMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectableMenuBehavior implements ActionListener {

    private final SelectableMenu menuListener;

    public SelectableMenuBehavior(SelectableMenu menuListener){
        this.menuListener = menuListener;
        System.out.println("listener init: "+menuListener.getHeaderMenu());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
        String selectedItem = (String)comboBox.getSelectedItem();

        System.out.println("menu listener : "+menuListener.getHeaderMenu()+
                " selected item: "+selectedItem);
        menuListener.setSelected(selectedItem);

        createMenu(selectedItem);
    }

    private void createMenu(String selectedItem) {
        MenuModel menuModel = new MenuModel(menuListener);
        menuModel.createModel(selectedItem);
    }
}
