package menuboxes;

import appcomponents.AbstractFactory;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;

public class NumbersMenu implements AbstractMenu {

    private static final String BOX_NAME = "номер";
    private static final String THEME_TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private JComponent jComponent;

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
    public JComponent getParent() {
        return jComponent;
    }

    @Override
    public void setParent(JComponent jComponent) {
        this.jComponent = jComponent;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public AbstractFactory getFactory() {
        return AbstractMenu.super::initialization;
    }

    @Override
    public String getCurrentMenu() {
        System.out.println("numbers select");
        return null;
    }

    @Override
    public void actionMenu(String currentMenu) {

    }
}
