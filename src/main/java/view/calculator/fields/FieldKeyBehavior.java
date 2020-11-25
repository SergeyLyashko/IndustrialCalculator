package view.calculator.fields;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class FieldKeyBehavior extends KeyAdapter {

    private final JFormattedTextField textField;
    private FieldSelectable field;

    FieldKeyBehavior(JFormattedTextField textField) {
        this.textField = textField;
    }

    void activate(FieldSelectable field) {
        this.field = field;
        textField.addKeyListener(this);
    }

    void deactivate() {
        textField.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            String text = textField.getText();
            //System.out.println("test press: "+text);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            if(field instanceof Width){
                textField.transferFocus();
                System.out.println("test width key");
            }
        }
    }

}
