package view.calculator.fields;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class FieldFocusBehavior extends FocusAdapter {

    private final JFormattedTextField textField;
    private static final String EMPTY_FIELD = "";
    private final FieldSelectable fieldSelectable;
    private final FieldKeyBehavior fieldKeyBehavior;

    FieldFocusBehavior(FieldSelectable fieldSelectable) {
        this.fieldSelectable = fieldSelectable;
        this.textField = (JFormattedTextField) fieldSelectable.getParent();
        fieldKeyBehavior = new FieldKeyBehavior(textField);
    }

    void activate(){
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
        textField.addFocusListener(this);
        fieldKeyBehavior.activate();
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
        fieldKeyBehavior.deactivate();
    }
}
