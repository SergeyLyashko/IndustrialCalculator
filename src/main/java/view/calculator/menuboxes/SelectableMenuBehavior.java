package view.calculator.menuboxes;

import view.calculator.SelectableMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SelectableMenuBehavior implements ActionListener {

    private final SelectableMenu menuListener;

    SelectableMenuBehavior(SelectableMenu menuListener){
        this.menuListener = menuListener;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
        String selectedItem = (String)comboBox.getSelectedItem();
        MenuModel menuModel = new MenuModel(menuListener, selectedItem);
    }
}
