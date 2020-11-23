package view.calculator.fields;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class FieldKeyBehavior extends KeyAdapter {

    private final JFormattedTextField textField;

    public FieldKeyBehavior(JFormattedTextField textField) {
        this.textField = textField;
    }

    public void activate() {
        textField.addKeyListener(this);
    }

    public void deactivate() {
        textField.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String text = textField.getText();
            System.out.println("test press: "+text);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            textField.transferFocus();
        }
    }
}
