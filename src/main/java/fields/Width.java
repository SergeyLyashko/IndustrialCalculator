package fields;

import appcomponents.FactoryableComponents;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;

public class Width implements SelectableField, AbstractField {

    private static final String BOX_NAME = "введите ширину";
    private static final String THEME_TOOL_TIP_TEXT = "поле ввода ширины детали";
    private static final int LOCATION_X = 190;
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
    public FactoryableComponents getFactory() {
        return AbstractField.super::ordered;
    }

    @Override
    public SwingComponent create() {
        return this;
    }
}
