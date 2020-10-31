package appview;

import calculator.CalculatorPanel;
import info.InfoPanel;
import settings.SettingsPanel;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class CommonPanel implements Serializable, SwingComponent {

    private static final long serialVersionUID = 1L;

    private JComponent componentSwing;
    private Tabbed tabbed;

    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        visitor.addVisitorComponent(this);
        this.tabbed = new Tabbed();
        List<SwingComponent> componentPanel = new ArrayList<>();
        addNewPanel("Калькулятор", visitor);
        addNewPanel("Настройки", visitor);
        addNewPanel("Справка", visitor);
        // TODO в список добавляется 1 компонент - таббед
        componentPanel.add(tabbed);
        return componentPanel;
    }

    private void addNewPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            SwingComponent createPanel(String type, Visitor visitor) {
                return createNewPanel(type);
            }
        };
        abstractPanel.order(type, visitor);
        JPanel abstractPanelComponent = abstractPanel.getComponentSwing();
        tabbed.addTab(type, abstractPanelComponent);
    }

    // TODO не используется
    @Override
    public JComponent getSwingComponent() {
        return componentSwing;
    }
/*
    @Override
    public void setComponentSwing(JComponent componentSwing) {
        this.componentSwing = componentSwing;
    }*/

    private SwingComponent createNewPanel(String type) {
        switch (type){
            case "Калькулятор":
                return new CalculatorPanel();
            case "Настройки":
                return new SettingsPanel();
            case "Справка":
                return new InfoPanel();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Override
    public String getName() {
        return "common";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public LayoutManager getLayout() {
        return new GridLayout(1, 1);
    }

    @Override
    public String getBorderLayout() {
        return null;
    }
}
