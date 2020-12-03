package view.model.behavior;

import view.model.*;
import view.view.AppComponent;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";
    private final FieldFocusBehavior fieldFocusBehavior;
    private final DocumentFilter defaultFilter;
    private final DigitalFilter digitalFilter;
    private final FieldKeyBehavior fieldKeyBehavior;
    private final ViewModelImpl viewModel;

    public FieldBehavior(ViewModelImpl viewModel){
        this.viewModel = viewModel;
        fieldFocusBehavior = new FieldFocusBehavior(this);
        defaultFilter = new DocumentFilter();
        digitalFilter = new DigitalFilter();
        fieldKeyBehavior = new FieldKeyBehavior();
    }

    public void fieldActivate(AppComponent component) {
        JTextField textField = (JFormattedTextField) component.getParent();
        textField.setText(component.getName());
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
        fieldFocusBehavior.activate(textField);
    }

    public void fieldDeactivate(AppComponent component) {
        JTextField parent = (JFormattedTextField) component.getParent();
        removeFilter(parent);
        parent.setText(component.getName());
        parent.setEditable(false);
        parent.setForeground(Color.GRAY);
        parent.setBackground(Color.LIGHT_GRAY);
        fieldFocusBehavior.deactivate(parent);
        fieldKeyBehavior.fieldDeactivate(parent);
    }

    public void areaActivate(AppComponent component){
        JTextField parent = (JFormattedTextField) component.getParent();
        fieldActivate(component);
        parent.setText(BOX_NAME_AREA);
    }

    void setFilter(JTextField textField){
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(digitalFilter);
    }

    private void removeFilter(JTextField textField){
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(defaultFilter);
    }

    void keyActivate(JTextField textField){
        fieldKeyBehavior.fieldActivate(textField);
    }

    void keyDeactivate(JTextField textField){
        fieldKeyBehavior.fieldDeactivate(textField);
    }

    public void registerObserver(CalculatorData receiveData) {
        fieldKeyBehavior.registerObserver(receiveData);
    }
}
