package view.model;

import view.controller.FieldSelectable;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class FieldKeyBehavior {

    private final ViewModelImpl viewModel;

    public FieldKeyBehavior(ViewModelImpl viewModel) {
        this.viewModel = viewModel;
    }

    void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            JTextField source = (JFormattedTextField) event.getSource();
            String text = source.getText();
            System.out.println("test press: "+text);
            viewModel.keyPressedValue(text);
        }
    }

    void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            JTextField source = (JFormattedTextField) event.getSource();
            source.transferFocus();
        }
    }

    void fieldActivate(FieldSelectable fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.addKeyListener((KeyListener) fieldSelectable);
    }

    void fieldDeactivate(FieldSelectable fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.removeKeyListener((KeyListener) fieldSelectable);
    }
}
