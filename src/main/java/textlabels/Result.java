package textlabels;

import appcomponents.AbstractFactory;
import appcomponents.Visitor;

import javax.swing.*;

public class Result implements AbstractLabel {

    private static final String DEFAULT_VIEW = "0.0";
    private static final String KG = "кг";
    private static final int LOCATION_X = 190;
    private static final int LOCATION_Y = 105;
    private static final int SIZE_X = 125;
    private static final int SIZE_Y = 25;
    private JLabel component;

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
        return null;
    }

    @Override
    public JComponent getParent() {
        return component;
    }

    @Override
    public <T extends JComponent> void setParent(T jComponent) {
        this.component = (JLabel) jComponent;
    }

    @Override
    public AbstractFactory getFactory() {
        return null;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {

    }
}
