package checkboxes;

import appcomponents.FactoryableComponents;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;

public class ComplexAreaCheckBox implements SelectableCheckBox, AbstractCheckBox {

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
    public void select(Visitor visitor) {
        // TODO
        System.out.println("complexArea selected");
    }

    @Override
    public void deselect(Visitor visitor) {
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
/*
    @Override
    public SwingComponent create() {
        return this;
    }

 */

    @Override
    public FactoryableComponents getFactory() {
        return AbstractCheckBox.super::ordered;
    }
}
