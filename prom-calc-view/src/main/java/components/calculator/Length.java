package components.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import components.common.ViewController;
import components.common.AppComponent;
import controller.FieldsAction;
import model.KeyActionObserver;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("length")
public class Length implements AppComponent, Comparable<AppComponent>, KeyActionObserver {

    private final JFormattedTextField textField;
    private static final int FOCUSED_RATE = 5;
    private static final String BOX_NAME = "введите длину";
    private static final String TOOL_TIP_TEXT = "поле ввода длины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;

    private ViewController viewController;
    private FieldsAction fieldsAction;

    @Autowired
    @Qualifier("lengthAction")
    public void setFieldsAction(FieldsAction fieldsAction){
        this.fieldsAction = fieldsAction;
    }

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    public Length(int locationX, int locationY){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setText(BOX_NAME);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        textField.setToolTipText(TOOL_TIP_TEXT);
        textField.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        fieldsAction.setComponent(this);
        fieldsAction.registerKeyObserver(this);
    }

    @Override
    public void keyActionUpdate() {
        viewController.keyActionUpdate();
    }

    @Override
    public JComponent getComponentParent() {
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
