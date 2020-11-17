package view.calculator.checkbox;

import view.*;

import javax.swing.*;
import java.awt.*;

public class ComplexAreaCheckBox implements AppComponent, CheckBoxSelectable, Host {

    private final JCheckBox jCheckBox;

    private static final String TOOL_TIP_BOX_TEXT = "расчет массы детали по задаваемой площади детали";
    private static final String BOX_NAME = "сложный периметр";
    private static final int LOCATION_X = 187;
    private static final int LOCATION_Y = 85;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    public ComplexAreaCheckBox(){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
        Font deriveFont = jCheckBox.getFont().deriveFont(10f);
        jCheckBox.setFont(deriveFont);
        CheckBoxState checkBoxState = new CheckBoxState(this);
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
    public void registerHost(Visitor visitor) {
        visitor.addHost(this);
    }

    @Override
    public void activate() {
        // TODO
        System.out.println("complexArea selected");
    }

    @Override
    public void deactivate() {
        // TODO
        System.out.println("complexArea deselected");
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public JComponent getParent() {
        return jCheckBox;
    }

}
