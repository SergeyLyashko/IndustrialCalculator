package view.model;

import view.view.AppComponent;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

class FieldFocusBehavior {

    private static final String EMPTY = "";
    private static final String BOX_NAME_AREA = "введите площадь";
    private final DocumentFilter defaultFilter;
    private final DigitalFilter digitalFilter;

    FieldFocusBehavior(){
        defaultFilter = new DocumentFilter();
        digitalFilter = new DigitalFilter();
    }

    void fieldActivate(AppComponent fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setText(fieldSelectable.getName());
        activate(fieldSelectable);
    }

    private void activate(AppComponent fieldSelectable){
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setEditable(true);
        parent.setForeground(Color.GRAY);
        parent.setBackground(Color.white);
        parent.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldFocusGained(fieldSelectable);
            }
        });
    }

    void fieldDeactivate(AppComponent fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        removeFilter(parent);
        parent.setText(fieldSelectable.getName());
        parent.setEditable(false);
        parent.setForeground(Color.GRAY);
        parent.setBackground(Color.LIGHT_GRAY);
        Arrays.stream(parent.getFocusListeners()).forEach(parent::removeFocusListener);

    }

    private void fieldFocusGained(AppComponent fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setForeground(Color.BLACK);
        parent.setText(EMPTY);
        setFilter(parent);
    }

    private void setFilter(JTextField textField){
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(digitalFilter);
    }

    private void removeFilter(JTextField textField){
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(defaultFilter);
    }

    void areaActivate(AppComponent fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setText(BOX_NAME_AREA);
        activate(fieldSelectable);
    }
}
