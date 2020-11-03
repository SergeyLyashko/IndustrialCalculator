package appview;

import calculator.CalculatorPanel;
import info.InfoPanel;
import settings.SettingsPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Tabbed implements SwingPanel {

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    @Override
    public String getName() {
        return "tabbed pane";
    }

    @Override
    public List<SwingPanel> getComponents(Visitor visitor) {
        List<SwingPanel> componentPanel = new ArrayList<>();
        SwingPanel calc = createPanel("Калькулятор", visitor);
        SwingPanel settings = createPanel("Настройки", visitor);
        SwingPanel info = createPanel("Справка", visitor);

        addToTab("Калькулятор", calc);
        addToTab("Настройки", settings);
        addToTab("Справка", info);

        componentPanel.add(this);
        return componentPanel;
    }

    private void addToTab(String type, SwingPanel component) {
        JComponent parentsComponent = component.getParentsComponent();
        tabbedPane.addTab(type, parentsComponent);
    }

    private SwingPanel createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingPanel createPanel(String type, Visitor visitor) {
                return createNewPanel(type);
            }
        };
        return abstractPanel.order(type, visitor);
    }

    private SwingPanel createNewPanel(String type) {
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
    public void acceptVisitor(Visitor visitor) {}

    @Override
    public JComponent getParentsComponent() {
        return tabbedPane;
    }
}
