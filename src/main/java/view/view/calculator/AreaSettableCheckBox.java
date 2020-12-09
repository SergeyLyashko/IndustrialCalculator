package view.view.calculator;

import view.controller.ViewController;
import view.view.AppComponent;
import view.view.Host;
import view.view.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

class AreaSettableCheckBox implements AppComponent, Host {

    private final JCheckBox jCheckBox;

    private static final String TOOL_TIP_TEXT = "расчет массы детали по задаваемой площади детали";
    private static final String BOX_NAME = "сложный периметр";
    private static final int LOCATION_X = 187;
    private static final int LOCATION_Y = 85;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    AreaSettableCheckBox(ViewController viewController){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(false);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
        setFont();
        addHost(viewController);
        addItemListener(viewController);
    }

    private void setFont(){
        Font deriveFont = jCheckBox.getFont().deriveFont(10f);
        jCheckBox.setFont(deriveFont);
    }

    private void addHost(ViewController viewController){
        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
    }

    private void addItemListener(ViewController viewController){
        jCheckBox.addItemListener(event ->
                viewController.areaCheckBoxState(event.getStateChange() == ItemEvent.SELECTED));
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getParent() {
        return jCheckBox;
    }

}
