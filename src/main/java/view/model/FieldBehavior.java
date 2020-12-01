package view.model;

import view.view.AppComponent;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;

class FieldBehavior {

    private static final String BOX_NAME_AREA = "введите площадь";
    private final FieldFocusBehavior fieldFocusBehavior;
    private final DocumentFilter defaultFilter;
    private final DigitalFilter digitalFilter;
    private final FieldKeyBehavior fieldKeyBehavior;
    private final ViewModelImpl viewModel;

    FieldBehavior(ViewModelImpl viewModel){
        this.viewModel = viewModel;
        fieldFocusBehavior = new FieldFocusBehavior(this);
        defaultFilter = new DocumentFilter();
        digitalFilter = new DigitalFilter();
        fieldKeyBehavior = new FieldKeyBehavior();
    }

    void fieldActivate(AppComponent fieldSelectable) {
        JTextField textField = (JFormattedTextField) fieldSelectable.getParent();
        textField.setEditable(true);
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.white);
        fieldFocusBehavior.activate(textField);
    }

    void fieldDeactivate(AppComponent fieldSelectable) {
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        removeFilter(parent);
        parent.setText(fieldSelectable.getName());
        parent.setEditable(false);
        parent.setForeground(Color.GRAY);
        parent.setBackground(Color.LIGHT_GRAY);
        fieldFocusBehavior.deactivate(parent);
        fieldKeyBehavior.fieldDeactivate(parent);
    }

    void areaActivate(AppComponent fieldSelectable){
        JTextField parent = (JFormattedTextField) fieldSelectable.getParent();
        parent.setText(BOX_NAME_AREA);
        fieldActivate(fieldSelectable);
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

    public void add(ReceiveDataObserver receiveData) {
        fieldKeyBehavior.registerObserver(receiveData);
    }
}
