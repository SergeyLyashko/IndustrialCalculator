package tabs;

import appcomponents.AbstractFactory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class PanelImpl implements SwingComponent, Panel {

    private JComponent jPanel;
    private List<SwingComponent> componentList;

    public SwingComponent create(List<SwingComponent> components, Visitor visitor){
        this.componentList = components;
        return this;
    }

    @Override
    public List<SwingComponent> getComponents() {
        return componentList;
    }

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
        return "Panel";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setParent(JComponent jComponent) {
        this.jPanel = jComponent;
    }

    @Override
    public AbstractFactory getFactory() {
        return Panel.super::ordered;
    }

    public Container getParent() {
        return jPanel;
    }
}
