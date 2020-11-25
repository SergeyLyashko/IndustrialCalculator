package view.view.fields;

import view.model.FieldFocusBehavior;
import view.model.FieldKeyBehavior;
import view.controller.FieldSelectable;

import javax.swing.*;

public class Length implements FieldSelectable {

    private static final String BOX_NAME_AREA = "введите площадь";
    private final JFormattedTextField textField;
    private final FieldFocusBehavior fieldFocusBehavior;
    private final FieldKeyBehavior fieldKeyBehavior;

    private static final String BOX_NAME = "введите длину";
    private static final String TOOL_TIP_TEXT = "поле ввода длины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 60;

    public Length(){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setText(BOX_NAME);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        textField.setToolTipText(TOOL_TIP_TEXT);
        fieldFocusBehavior = new FieldFocusBehavior(this);
        fieldFocusBehavior.deactivate();
        fieldKeyBehavior = new FieldKeyBehavior(textField);
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
        //System.out.println("length activate");
        textField.setText(BOX_NAME);
        fieldFocusBehavior.activate();
        fieldKeyBehavior.activate(this);
    }

    @Override
    public void deactivate() {
        //System.out.println("length Deactivate");
        textField.setText(BOX_NAME);
        fieldFocusBehavior.deactivate();
        fieldKeyBehavior.deactivate();
    }

    @Override
    public void transformArea(){
        System.out.println("length transform");
        textField.setText(BOX_NAME_AREA);
        fieldFocusBehavior.activate();
        fieldKeyBehavior.activate(this);
    }

    @Override
    public String getName() {
        return BOX_NAME;
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
