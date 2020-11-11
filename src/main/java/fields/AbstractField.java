package fields;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractField {

    int WIDTH = 125;
    int HEIGHT = 23;

    default JComponent getField(){
        JFormattedTextField textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        return textField;
    }

    SwingComponent create();

    default SwingComponent ordered(SwingComponent component, Visitor visitor){
        JComponent jComponent = getField();
        setLocation(component, jComponent);
        addListener(component, jComponent);
        component.setParent(jComponent);
        return component;
    }

    default void setLocation(SwingComponent selectableField, JComponent component) {
        int locationX = selectableField.getLocationX();
        int locationY = selectableField.getLocationY();
        component.setLocation(locationX, locationY);
    }

    default void addListener(SwingComponent selectableField, JComponent component) {
        FieldState fieldState = new FieldState(selectableField);
        component.addFocusListener(fieldState);
        component.addKeyListener(fieldState);
    }
}
