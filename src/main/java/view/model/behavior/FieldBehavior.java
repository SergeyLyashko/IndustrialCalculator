package view.model.behavior;

import view.model.*;
import view.view.AppComponent;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";
    private final FocusBehavior focusBehavior;
    private final DocumentFilter defaultFilter;
    private final DigitalFilter digitalFilter;
    private final KeyBehavior keyBehavior;

    public FieldBehavior(){
        focusBehavior = new FocusBehavior(this);
        defaultFilter = new DocumentFilter();
        digitalFilter = new DigitalFilter();
        keyBehavior = new KeyBehavior();
    }

    public void fieldActivate(AppComponent component) {
        JTextField textField = (JFormattedTextField) component.getParent();
        textField.setText(component.getName());
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
        focusBehavior.activate(textField);
    }

    public void areaActivate(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        parent.setText(BOX_NAME_AREA);
    }

    public void areaDeactivate(AppComponent component){
        JTextField textField = (JFormattedTextField) component.getParent();
        textField.setText(component.getName());
    }

    public void keyActivate(JTextField textField){
        keyBehavior.fieldActivate(textField);
    }

    public void fieldDeactivate(AppComponent component) {
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

    public void registerObserver(KeyActionObserver observer) {
        keyBehavior.registerObserver(observer);
    }
}
