package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import viewcomponents.common.AppComponent;
import viewcontroller.FieldsAction;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("width")
public class Width implements AppComponent, Comparable<AppComponent> {

    private static final int FOCUSED_RATE = 4;
    private static final String BOX_NAME = "введите ширину";
    private static final String TOOL_TIP_TEXT = "поле ввода ширины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;
    private final JFormattedTextField textField;

    private FieldsAction fieldsAction;

    @Autowired
    @Qualifier("widthAction")
    public void setFieldsAction(FieldsAction fieldsAction){
        this.fieldsAction = fieldsAction;
    }

    @PostConstruct
    private void afterPropertiesSet() {
        fieldsAction.setComponent(this);
    }

    public Width(int locationX, int locationY){
        textField = new JFormattedTextField();
        textField.setSize(WIDTH, HEIGHT);
        textField.setEditable(false);
        textField.setText(BOX_NAME);
        textField.setToolTipText(TOOL_TIP_TEXT);
        textField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        textField.setLocation(locationX, locationY);
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