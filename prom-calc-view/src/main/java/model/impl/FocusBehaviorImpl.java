package model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import model.Filter;
import model.FocusBehavior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

@Service("focusBehavior")
@Scope("prototype")
class FocusBehaviorImpl implements FocusBehavior {

    private static final String EMPTY = "";
    @Autowired
    @Qualifier("digitalFilter")
    private Filter digitalFilter;

    @Override
    public void fieldActivate(JComponent component){
        if(component instanceof JFormattedTextField){
            JFormattedTextField textField = (JFormattedTextField) component;
            textField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    digitalFilter.activateFilter(component);
                    fieldFocusGained(textField);
                }
            });
        }
    }

    @Override
    public void fieldDeactivate(JComponent component) {
        if(component instanceof JFormattedTextField){
            JFormattedTextField textField = (JFormattedTextField) component;
            Arrays.stream(textField.getFocusListeners()).forEach(textField::removeFocusListener);
        }
    }

    private void fieldFocusGained(JFormattedTextField textField) {
        textField.setForeground(Color.BLACK);
        textField.setText(EMPTY);
    }
}
