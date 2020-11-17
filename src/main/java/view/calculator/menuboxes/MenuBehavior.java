package view.calculator.menuboxes;

import view.calculator.SelectableMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBehavior implements ActionListener {

    private final SelectableMenu menuListener;

    public MenuBehavior(SelectableMenu menuListener){
        this.menuListener = menuListener;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
        String selectedItem = (String)comboBox.getSelectedItem();
        createMenu(menuListener, selectedItem);
    }

    private void createMenu(SelectableMenu menuListener, String selectedItem) {
        MenuModel menuModel = new MenuModel();
        menuModel.createModel(menuListener, selectedItem);
    }
}
