package fields;

import appcomponents.SelectableComponent;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractField extends SelectableComponent {

    int WIDTH = 125;
    int HEIGHT = 23;

    default SwingComponent ordered(SwingComponent component, Visitor visitor){
        JComponent jComponent = getField();
        setLocation(component, jComponent);
        addListener(component, visitor, jComponent);
        component.setParent(jComponent);
        return component;
    }

    default JComponent getField(){
        JFormattedTextField textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        return textField;
    }

    default void setLocation(SwingComponent selectableField, JComponent component) {
        int locationX = selectableField.getLocationX();
        int locationY = selectableField.getLocationY();
        component.setLocation(locationX, locationY);
    }

    default void addListener(SwingComponent selectableField, Visitor visitor, JComponent component) {
        FieldState fieldState = new FieldState(selectableField, visitor);
        component.addFocusListener(fieldState);
        component.addKeyListener(fieldState);
    }
}
