package settings;

import appview.SwingComponent;
import appview.Visitor;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class ToolTip implements Serializable, SelectableCheckBox {

    private static final long serialVersionUID = 1L;

    private static final String BOX_NAME = "включить всплывающие подсказки";
    private static final String TOOL_TIP_BOX_TEXT = "включение/отключение всплывающих подсказок";
    private static final int LOCATION_X = 15;
    private static final int LOCATION_Y = 60;
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

    // TODO delete this
    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        visitor.addVisitorComponent(this);
        return new ArrayList<>();
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return null;
    }

    @Override
    public String getBorderLayout() {
        return null;
    }

    @Override
    public JComponent getSwingComponent() {
        return componentSwing;
    }

    @Override
    public void setComponentSwing(JCheckBox componentSwing) {
        this.componentSwing = componentSwing;
    }

    @Override
    public void select() {
        // TODO
        System.out.println("toolTip selected");
        setState(true);
    }

    @Override
    public void deselect() {
        // TODO
        System.out.println("toolTip deselected");
        setState(false);
    }

    private void setState(boolean state) {
        ToolTipManager.sharedInstance().setEnabled(state);
    }

}
