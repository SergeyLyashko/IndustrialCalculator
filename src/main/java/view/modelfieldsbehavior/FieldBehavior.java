package view.modelfieldsbehavior;

import view.AppComponent;
import view.viewmodel.KeyActionObserver;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;

class FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";
    private final FocusBehavior focusBehavior;
    private final DocumentFilter defaultFilter;
    private final DigitalFilter digitalFilter;
    private final KeyBehavior keyBehavior;
    private final AppComponent component;

    FieldBehavior(AppComponent component){
        this.component = component;
        focusBehavior = new FocusBehavior(this);
        defaultFilter = new DocumentFilter();
        digitalFilter = new DigitalFilter();
        keyBehavior = new KeyBehavior();
    }

    void fieldActivate() {
        JTextField textField = (JFormattedTextField) component.getParent();
        textField.setText(component.getName());
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
        focusBehavior.activate(textField);
    }

    void areaActivate(){
        JTextField parent = (JFormattedTextField) component.getParent();
        parent.setText(BOX_NAME_AREA);
    }

    void areaDeactivate(){
        JTextField textField = (JFormattedTextField) component.getParent();
        textField.setText(component.getName());
    }

    void keyActivate(JTextField textField){
        keyBehavior.fieldActivate(textField);
    }

    void fieldDeactivate() {
        JTextField parent = (JFormattedTextField) component.getParent();
        removeFilter(parent);
        parent.setText(component.getName());
        parent.setEditable(false);
        parent.setForeground(Color.GRAY);
        parent.setBackground(Color.LIGHT_GRAY);
        focusBehavior.deactivate(parent);
        keyBehavior.fieldDeactivate(parent);
    }

    void setFilter(JTextField textField){
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(digitalFilter);
    }

    private void removeFilter(JTextField textField){
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(defaultFilter);
    }

    void keyDeactivate(JTextField textField){
        keyBehavior.fieldDeactivate(textField);
    }

    void registerObserver(KeyActionObserver observer) {
        keyBehavior.registerObserver(observer);
    }
}
