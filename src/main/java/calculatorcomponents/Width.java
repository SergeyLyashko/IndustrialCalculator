package calculatorcomponents;

import view.ViewController;
import view.AppComponent;
import javax.swing.*;

class Width implements AppComponent, Comparable<AppComponent>  {

    private final JFormattedTextField textField;

    private static final int FOCUSED_RATE = 4;
    private static final String BOX_NAME = "введите ширину";
    private static final String TOOL_TIP_TEXT = "поле ввода ширины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 20;

    Width(ViewController viewController){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setText(BOX_NAME);
        textField.setToolTipText(TOOL_TIP_TEXT);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);

        viewController.setWidth(this);
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

    @Override
    public int getFocusedRate() {
        return FOCUSED_RATE;
    }

    @Override
    public int compareTo(AppComponent o) {
        return this.getFocusedRate() - o.getFocusedRate();
    }
}