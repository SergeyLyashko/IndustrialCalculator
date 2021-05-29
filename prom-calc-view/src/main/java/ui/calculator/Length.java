package ui.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import model.ViewController;
import ui.AppComponent;
import controller.FieldsAction;
import model.KeyActionObserver;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component("length")
public class Length extends JFormattedTextField implements AppComponent, Comparable<AppComponent>, KeyActionObserver {

    private static final int FOCUSED_RATE = 5;
    private static final String BOX_NAME = "введите длину";
    private static final String TOOL_TIP_TEXT = "поле ввода длины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;

    @Autowired
    private ViewController viewController;

    @Autowired
    @Qualifier("lengthAction")
    private FieldsAction fieldsAction;

    public Length(int locationX, int locationY){
        super.setSize(WIDTH, HEIGHT);
        super.setEditable(false);
        super.setText(BOX_NAME);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setLocation(locationX, locationY);
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
