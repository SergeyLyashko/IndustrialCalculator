package view.calculator.fields;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class FieldFocusBehavior extends FocusAdapter {

    private final JFormattedTextField textField;
    private static final String EMPTY_FIELD = "";
    private final FieldSelectable fieldSelectable;


    FieldFocusBehavior(FieldSelectable fieldSelectable) {
        this.fieldSelectable = fieldSelectable;
        this.textField = (JFormattedTextField) fieldSelectable.getParent();
    }

    void activate(){
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
        textField.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        textField.setForeground(Color.BLACK);
        textField.setText(EMPTY_FIELD);
    }

    void deactivate() {
        textField.removeFocusListener(this);
        textField.setEditable(false);
        textField.setText(fieldSelectable.getName());
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.LIGHT_GRAY);
    }
}
