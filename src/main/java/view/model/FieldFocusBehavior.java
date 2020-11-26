package view.model;

import view.controller.FieldSelectable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;

class FieldFocusBehavior {

    private static final String EMPTY = "";

    void fieldActivate(FieldSelectable fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setEditable(true);
        parent.setForeground(Color.GRAY);
        parent.setBackground(Color.white);
        parent.addFocusListener((FocusListener) fieldSelectable);
    }

    void fieldDeactivate(FieldSelectable fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setEditable(false);
        parent.setForeground(Color.GRAY);
        parent.setBackground(Color.LIGHT_GRAY);
        parent.removeFocusListener((FocusListener) fieldSelectable);
    }

    void fieldFocusGained(FieldSelectable fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setForeground(Color.BLACK);
        parent.setText(EMPTY);
    }
}
