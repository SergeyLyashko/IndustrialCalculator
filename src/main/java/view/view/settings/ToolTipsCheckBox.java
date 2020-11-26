package view.view.settings;

import view.controller.ViewController;
import view.view.AppComponent;
import view.controller.Host;
import view.controller.Visitor;

import javax.swing.*;
import java.awt.event.ItemEvent;

class ToolTipsCheckBox implements AppComponent, Host {

    private final JCheckBox jCheckBox;

    private static final String BOX_NAME = "включить всплывающие подсказки";
    private static final String TOOL_TIP_TEXT = "включение/отключение всплывающих подсказок";
    private static final int LOCATION_X = 15;
    private static final int LOCATION_Y = 60;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    ToolTipsCheckBox(ViewController viewController){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        jCheckBox.setToolTipText(TOOL_TIP_TEXT);
        jCheckBox.addItemListener(event ->
                ToolTipManager.sharedInstance().setEnabled(event.getStateChange() == ItemEvent.SELECTED));

        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
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
