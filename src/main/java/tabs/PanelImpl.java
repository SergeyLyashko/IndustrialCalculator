package tabs;

import appcomponents.FactoryableComponents;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class PanelImpl implements SwingComponent, Panel {

    private JComponent jPanel;
    private LayoutManager layoutManager;
    private String borderLayout;
    private String panelName;
    private List<SwingComponent> componentList;

    public SwingComponent create(String type, List<SwingComponent> components, Visitor visitor){
        switch (type){
            case "Калькулятор":
                return createCalculatorPanel(components, visitor);
            case "Настройки":
                return createSettingsPanel(components, visitor);
            case "Справка":
                return createInfoPanel(components, visitor);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private SwingComponent createSettingsPanel(List<SwingComponent> components, Visitor visitor){
        setName("Настройки");
        setComponentsList(components);
        return this;
    }

    private SwingComponent createInfoPanel(List<SwingComponent> components, Visitor visitor) {
        setLayout(new BorderLayout());
        setBorderLayout(BorderLayout.CENTER);
        setName("Справка");
        setComponentsList(components);
        return this;
    }

    private SwingComponent createCalculatorPanel(List<SwingComponent> components, Visitor visitor){
        setName("Калькулятор");
        setComponentsList(components);
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
    public LayoutManager getLayout() {
        return layoutManager;
    }

    public String getBorderLayout(){
        return borderLayout;
    }

    @Override
    public void setParent(JComponent jComponent) {
        this.jPanel = jComponent;
    }

    @Override
    public FactoryableComponents getFactory() {
        return Panel.super::ordered;
    }

    public Container getParent() {
        return jPanel;
    }


    public void setName(String panelName) {
        this.panelName = panelName;
    }

    public void setComponentsList(List<SwingComponent> componentsList) {
        this.componentList = componentsList;
    }

    public void setLayout(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setBorderLayout(String borderLayout) {
        this.borderLayout = borderLayout;
    }
}
