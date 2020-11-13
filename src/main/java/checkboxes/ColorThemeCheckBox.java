package checkboxes;

import appcomponents.AbstractFactory;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ColorThemeCheckBox implements Serializable, AbstractCheckBox {

    private static final long serialVersionUID = 1L;

    private static final String BOX_NAME = "темная тема оформления";
    private static final String THEME_TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private static final int LOCATION_X = 15;
    private static final int LOCATION_Y = 35;
    private Color backGround;
    private Color foreGround;
    private Color markerColor;
    private Color serviceStringColor;
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
    public JComponent getParent() {
        return componentSwing;
    }

    @Override
    public void setParent(JComponent jComponent) {
        this.componentSwing = jComponent;
    }

    @Override
    public AbstractFactory getFactory() {
        return AbstractCheckBox.super::ordered;
    }

    @Override
    public void activate(Visitor visitor) {
        setDarkColorTheme();
        visitor.raid();
    }

    @Override
    public void deactivate(Visitor visitor) {
        setLightColorTheme();
        visitor.raid();
    }

    private void setDarkColorTheme() {
        System.out.println("theme set dark");
        backGround = Color.BLACK;
        foreGround = Color.WHITE;
        markerColor = Color.WHITE;
        serviceStringColor = Color.GREEN;
    }

    private void setLightColorTheme() {
        System.out.println("theme set light");
        backGround = new Color(250, 236, 229);
        foreGround = Color.BLACK;
        markerColor = Color.BLACK;
        serviceStringColor = Color.BLUE;
    }
}
