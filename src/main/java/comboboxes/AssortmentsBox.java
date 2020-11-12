package comboboxes;

import appcomponents.AbstractFactory;
import appcomponents.SelectableComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;

public class AssortmentsBox implements SelectableComponent, AbstractComboBox {

    private static final String BOX_NAME = "сортамент";
    private static final String THEME_TOOL_TIP_TEXT = "выбор сортамента детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private JComponent componentSwing;

    @Override
    public int getLocationX() {
        return LOCATION_X;
    }

    @Override
    public int getLocationY() {
        return LOCATION_Y;
    }

    @Override
    public String getName() {
        return BOX_NAME;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Container getParent() {
        return componentSwing;
    }

    @Override
    public void setParent(JComponent jComponent) {
        this.componentSwing = jComponent;
    }

    @Override
    public AbstractFactory getFactory() {
        return AbstractComboBox.super::ordered;
    }

    @Override
    public void activate(Visitor visitor) {

    }

    @Override
    public void deactivate(Visitor visitor) {

    }
}
