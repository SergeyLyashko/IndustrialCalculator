package view.view.calculator;

import view.controller.ViewController;
import view.controller.FieldSelectable;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Length extends FocusAdapter implements KeyListener, FieldSelectable {

    private static final String BOX_NAME_AREA = "введите площадь";
    private final JFormattedTextField textField;

    private static final String BOX_NAME = "введите длину";
    private static final String TOOL_TIP_TEXT = "поле ввода длины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 60;
    private final ViewController viewController;

    Length(ViewController viewController){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setText(BOX_NAME);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        textField.setToolTipText(TOOL_TIP_TEXT);

        this.viewController = viewController;
        viewController.fieldDeactivate(this);
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
        System.out.println("length activate");
        textField.setText(BOX_NAME);
        viewController.fieldActivate(this);
    }

    @Override
    public void deactivate() {
        System.out.println("length Deactivate");
        textField.setText(BOX_NAME);
        viewController.fieldDeactivate(this);
    }

    @Override
    public void focusGained(FocusEvent event) {
        viewController.fieldFocusGained(this);
    }

    @Override
    public void transformArea(){
        System.out.println("length transform");
        textField.setText(BOX_NAME_AREA);
        viewController.fieldActivate(this);
    }

    @Override
    public String getName() {
        return BOX_NAME;
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent event) {
        viewController.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        viewController.keyReleased(event);
    }
}
