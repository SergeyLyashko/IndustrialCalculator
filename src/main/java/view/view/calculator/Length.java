package view.view.calculator;

import view.controller.ViewController;
import view.view.AppComponent;
import javax.swing.*;

class Length implements AppComponent {

    private final JFormattedTextField textField;

    private static final String BOX_NAME = "введите длину";
    private static final String TOOL_TIP_TEXT = "поле ввода длины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 60;
    private final ViewController viewController;

    Length(ViewController viewController){
        this.viewController = viewController;
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setText(BOX_NAME);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        textField.setToolTipText(TOOL_TIP_TEXT);

        viewController.setStateTarget(this);
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
    public String getName() {
        return BOX_NAME;
    }

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

}
