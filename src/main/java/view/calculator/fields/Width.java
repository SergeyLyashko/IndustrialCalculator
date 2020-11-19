package view.calculator.fields;

import javax.swing.*;

public class Width implements FieldSelectable {

    private final JFormattedTextField textField;

    private static final String BOX_NAME = "введите ширину";
    private static final String THEME_TOOL_TIP_TEXT = "поле ввода ширины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 20;
    private final FieldFocusBehavior fieldFocusBehavior;

    public Width(){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setText(BOX_NAME);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        fieldFocusBehavior = new FieldFocusBehavior(this);
        fieldFocusBehavior.deactivate();
    }

    @Override
    public int getLocationX() {
        return LOCATION_X;
    }

    @Override
    public int getLocationY() {
        return LOCATION_Y;
    }

    @Override
    public JComponent getParent() {
        return textField;
    }

    @Override
    public void activate() {
        System.out.println("width activate");
        fieldFocusBehavior.activate();
    }

    @Override
    public void deactivate() {
        System.out.println("width Deactivate");
        fieldFocusBehavior.deactivate();
    }

    @Override
    public String getName() {
        return BOX_NAME;
    }

}