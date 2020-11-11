package tabs;

import appcomponents.AbstractFactory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class PanelImpl implements SwingComponent, Panel {

    private JComponent jPanel;
    private String panelName;
    private List<SwingComponent> componentList;

    public SwingComponent create(String type, List<SwingComponent> components, Visitor visitor){
        switch (type){
            case "Калькулятор":
                return createCalculatorPanel(type,components, visitor);
            case "Настройки":
                return createSettingsPanel(type, components, visitor);
            case "Справка":
                return createInfoPanel(type, components, visitor);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private SwingComponent createSettingsPanel(String type, List<SwingComponent> components, Visitor visitor){
        this.panelName = type;
        this.componentList = components;
        return this;
    }

    private SwingComponent createInfoPanel(String type, List<SwingComponent> components, Visitor visitor) {
        this.panelName = type;
        this.componentList = components;
        return this;
    }

    private SwingComponent createCalculatorPanel(String type, List<SwingComponent> components, Visitor visitor){
        this.panelName = type;
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
        return panelName;
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
