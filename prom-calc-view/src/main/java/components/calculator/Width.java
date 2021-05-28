package components.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import components.common.AppComponent;
import controller.FieldsAction;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("width")
public class Width extends JFormattedTextField implements AppComponent, Comparable<AppComponent> {

    private static final int FOCUSED_RATE = 4;
    private static final String BOX_NAME = "введите ширину";
    private static final String TOOL_TIP_TEXT = "поле ввода ширины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;

    @Autowired
    @Qualifier("widthAction")
    private FieldsAction fieldsAction;

    @PostConstruct
    private void afterPropertiesSet() {
        fieldsAction.setComponent(this);
    }

    public Width(int locationX, int locationY){
        super.setSize(WIDTH, HEIGHT);
        super.setEditable(false);
        super.setText(BOX_NAME);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
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