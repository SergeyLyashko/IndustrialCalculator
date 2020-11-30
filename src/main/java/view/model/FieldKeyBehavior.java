package view.model;

import view.view.AppComponent;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

class FieldKeyBehavior {

    private final ViewModelImpl viewModel;

    public FieldKeyBehavior(ViewModelImpl viewModel) {
        this.viewModel = viewModel;
    }

    void fieldActivate(AppComponent fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                pressed(event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                released(event);
            }
        });
    }

    private void pressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            JTextField source = (JFormattedTextField) event.getSource();
            String text = source.getText();
            System.out.println("test press: "+text);
            //TODO!

        }
    }

    private void released(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            JTextField source = (JFormattedTextField) event.getSource();
            source.transferFocus();
        }
    }

    void fieldDeactivate(AppComponent fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        Arrays.stream(parent.getKeyListeners()).forEach(parent::removeKeyListener);
    }
}
