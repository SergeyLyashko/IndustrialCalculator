package view.model;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

class FieldKeyBehavior {

    void fieldActivate(JTextField parent) {
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

    void fieldDeactivate(JTextField parent) {
        Arrays.stream(parent.getKeyListeners()).forEach(parent::removeKeyListener);
    }
}
