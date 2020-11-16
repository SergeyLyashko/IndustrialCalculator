package view.settings.checkboxes;

import view.AppComponent;
import view.CheckBoxSelectable;
import view.CheckBoxState;
import view.Visitor;

import javax.swing.*;

public class ToolTipsCheckBox implements AppComponent, CheckBoxSelectable {

    private final JCheckBox jCheckBox;

    private static final String BOX_NAME = "включить всплывающие подсказки";
    private static final String TOOL_TIP_BOX_TEXT = "включение/отключение всплывающих подсказок";
    private static final int LOCATION_X = 15;
    private static final int LOCATION_Y = 60;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    public ToolTipsCheckBox(){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
    }

    @Override
    public void addListener(AppComponent component, Visitor visitor) {
        CheckBoxState checkBoxState = new CheckBoxState(this, visitor);
        jCheckBox.addItemListener(checkBoxState);
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
        visitor.visit(this);
    }

    @Override
    public JComponent getParent() {
        return jCheckBox;
    }

    @Override
    public void activate(Visitor visitor) {
        // TODO
        System.out.println("toolTip selected");
        setState(true);
        visitor.raid();
    }

    @Override
    public void deactivate(Visitor visitor) {
        // TODO
        System.out.println("toolTip deselected");
        setState(false);
        visitor.raid();
    }

    private void setState(boolean state) {
        ToolTipManager.sharedInstance().setEnabled(state);
    }

}
