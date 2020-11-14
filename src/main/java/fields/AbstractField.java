package fields;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractField extends SwingComponent, FieldSelectable {

    int WIDTH = 125;
    int HEIGHT = 23;

    default SwingComponent ordered(SwingComponent component, Visitor visitor){
        JFormattedTextField jFormattedText = getField();
        setLocation(component, jFormattedText);
        addListener(component, visitor, jFormattedText);
        component.setParent(jFormattedText);
        return component;
    }

    default JFormattedTextField getField(){
        JFormattedTextField textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        return textField;
    }

    default void setLocation(SwingComponent component, JFormattedTextField jFormattedTextField) {
        int locationX = component.getLocationX();
        int locationY = component.getLocationY();
        jFormattedTextField.setLocation(locationX, locationY);
    }

    default void addListener(SwingComponent component, Visitor visitor, JFormattedTextField jFormattedTextField) {
        FieldBehavior fieldState = new FieldBehavior(component, visitor);
        jFormattedTextField.addFocusListener(fieldState);
        jFormattedTextField.addKeyListener(fieldState);
    }
}
