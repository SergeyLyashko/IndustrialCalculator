package settings;

import appview.Visitor;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

class ColorTheme implements Serializable, SelectableCheckBox {

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
    public JComponent getParentsComponent() {
        return componentSwing;
    }

    @Override
    public void setParentComponent(JCheckBox componentSwing) {
        this.componentSwing = componentSwing;
    }

    @Override
    public void select() {
        // TODO
        System.out.println("theme selected");
        setDarkColorTheme();
    }

    @Override
    public void deselect() {
        // TODO
        System.out.println("theme deselected");
        setLightColorTheme();
    }

    private void setDarkColorTheme() {
        backGround = Color.BLACK;
        foreGround = Color.WHITE;
        markerColor = Color.WHITE;
        serviceStringColor = Color.GREEN;
    }

    private void setLightColorTheme() {
        backGround = new Color(250, 236, 229);
        foreGround = Color.BLACK;
        markerColor = Color.BLACK;
        serviceStringColor = Color.BLUE;
    }
}
