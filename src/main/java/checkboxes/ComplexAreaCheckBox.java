package checkboxes;

import appcomponents.AbstractFactory;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;

public class ComplexAreaCheckBox implements AbstractCheckBox {

    private static final String TOOL_TIP_BOX_TEXT = "расчет массы детали по задаваемой площади детали";
    private static final String BOX_NAME = "сложный периметр";
    private static final int LOCATION_X = 187;
    private static final int LOCATION_Y = 85;
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
    public void activate(Visitor visitor) {
        // TODO
        System.out.println("complexArea selected");
    }

    @Override
    public void deactivate(Visitor visitor) {
        // TODO
        System.out.println("complexArea deselected");
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public JComponent getParent() {
        return componentSwing;
    }

    @Override
    public void setParent(JComponent jComponent) {
        Font deriveFont = jComponent.getFont().deriveFont(10f);
        jComponent.setFont(deriveFont);
        this.componentSwing = jComponent;
    }

    @Override
    public AbstractFactory getFactory() {
        return AbstractCheckBox.super::initialization;
    }
}
