package viewcomponents.fields;

import viewcomponents.SwingComponent;
import viewcomponents.Visitor;

import javax.swing.*;

public class Length implements SwingComponent {

    private final JFormattedTextField textField;

    private static final String BOX_NAME = "введите длину";
    private static final String THEME_TOOL_TIP_TEXT = "поле ввода длины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 60;

    public Length(){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addListener(SwingComponent component, Visitor visitor) {
        FieldBehavior fieldState = new FieldBehavior(component, visitor);
        textField.addFocusListener(fieldState);
        textField.addKeyListener(fieldState);
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

}
