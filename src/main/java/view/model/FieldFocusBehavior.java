package view.model;

import view.controller.FieldSelectable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FieldFocusBehavior extends FocusAdapter {

    private final JFormattedTextField textField;
    private static final String EMPTY_FIELD = "";
    private final FieldSelectable fieldSelectable;


    public FieldFocusBehavior(FieldSelectable fieldSelectable) {
        this.fieldSelectable = fieldSelectable;
        this.textField = (JFormattedTextField) fieldSelectable.getParent();
    }

    public void activate(){
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

    public void deactivate() {
        textField.removeFocusListener(this);
        textField.setEditable(false);
        textField.setText(fieldSelectable.getName());
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.LIGHT_GRAY);
    }
}
