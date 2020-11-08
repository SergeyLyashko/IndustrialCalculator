package fields;

import javax.swing.*;

public abstract class AbstractField {

    private final JFormattedTextField textField;
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;

    public AbstractField(){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
    }

    public abstract SelectableField create();

    public SelectableField orderedField(){
        SelectableField selectableField = create();
        setLocation(selectableField);
        addListener(selectableField);
        selectableField.setParent(textField);
        return selectableField;
    }

    private void addListener(SelectableField selectableField) {
        FieldState fieldState = new FieldState(selectableField);
        textField.addFocusListener(fieldState);
        textField.addKeyListener(fieldState);
    }

    private void setLocation(SelectableField selectableField) {
        int locationX = selectableField.getLocationX();
        int locationY = selectableField.getLocationY();
        textField.setLocation(locationX, locationY);
    }
}
