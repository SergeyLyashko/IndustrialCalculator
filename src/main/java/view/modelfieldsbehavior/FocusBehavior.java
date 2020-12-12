package view.modelfieldsbehavior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

class FocusBehavior {

    private static final String EMPTY = "";
    private final FieldBehavior fieldBehavior;

    FocusBehavior(FieldBehavior fieldBehavior){
        this.fieldBehavior = fieldBehavior;
    }

    void activate(JTextField textField){
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldFocusGained(textField);
                fieldBehavior.setFilter(textField);
                fieldBehavior.keyActivate(textField);
            }

            @Override
            public void focusLost(FocusEvent e) {
                fieldBehavior.keyDeactivate(textField);
            }
        });
    }

    void deactivate(JTextField textField) {
        Arrays.stream(textField.getFocusListeners()).forEach(textField::removeFocusListener);
    }

    private void fieldFocusGained(JTextField textField) {
        textField.setForeground(Color.BLACK);
        textField.setText(EMPTY);
    }
}
