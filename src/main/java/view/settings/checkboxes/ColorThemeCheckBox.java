package view.settings.checkboxes;

import view.AppComponent;
import view.CheckBoxSelectable;
import view.CheckBoxState;
import view.Visitor;

import javax.swing.*;
import java.awt.*;

public class ColorThemeCheckBox implements AppComponent, CheckBoxSelectable {

    private final JCheckBox jCheckBox;

    private static final String BOX_NAME = "темная тема оформления";
    private static final String THEME_TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private static final int LOCATION_X = 15;
    private static final int LOCATION_Y = 35;
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;
    private Color backGround;
    private Color foreGround;
    private Color markerColor;
    private Color serviceStringColor;

    public ColorThemeCheckBox(){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(WIDTH, HEIGHT);
        jCheckBox.setText(BOX_NAME);
    }

    @Override
    public void addListener(Visitor visitor) {
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
