package textlabels;

import appcomponents.AbstractFactory;
import appcomponents.Visitor;

import javax.swing.*;

public class Message implements AbstractLabel {

    private static final String DEFAULT_VIEW = "";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 140;
    private static final int SIZE_X = 315;
    private static final int SIZE_Y = 15;
    private JLabel component;

    @Override
    public int getLocationX() {
        return 0;
    }

    @Override
    public int getLocationY() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public JComponent getParent() {
        return null;
    }

    @Override
    public <T extends JComponent> void setParent(T jComponent) {

    }

    @Override
    public AbstractFactory getFactory() {
        return null;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {

    }
}
