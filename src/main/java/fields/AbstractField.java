package fields;

import appcomponents.SwingComponent;

import javax.swing.*;

abstract class AbstractField {

    private final JFormattedTextField textField;
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;

    AbstractField(){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
    }

    abstract SwingComponent create();

    SwingComponent orderedField(){
        SwingComponent selectableField = create();
        setLocation(selectableField);
        addListener(selectableField);
        selectableField.setParent(textField);
        return selectableField;
    }

    private void addListener(SwingComponent selectableField) {
        FieldState fieldState = new FieldState(selectableField);
        textField.addFocusListener(fieldState);
        textField.addKeyListener(fieldState);
    }

    private void setLocation(SwingComponent selectableField) {
        int locationX = selectableField.getLocationX();
        int locationY = selectableField.getLocationY();
        textField.setLocation(locationX, locationY);
    }

}
