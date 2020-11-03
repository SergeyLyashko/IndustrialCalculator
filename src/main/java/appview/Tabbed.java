package appview;

import calculator.CalculatorPanel;
import info.InfoPanel;
import settings.SettingsPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Tabbed implements SwingComponent {

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    @Override
    public String getName() {
        return "tabbed pane";
    }

    @Override
    public List<SwingComponent> getComponents(Visitor visitor) {
        List<SwingComponent> componentPanel = new ArrayList<>();
        SwingComponent calc = createPanel("Калькулятор", visitor);
        SwingComponent settings = createPanel("Настройки", visitor);
        SwingComponent info = createPanel("Справка", visitor);

        addToTab("Калькулятор", calc);
        addToTab("Настройки", settings);
        addToTab("Справка", info);

        componentPanel.add(this);
        return componentPanel;
    }

    private void addToTab(String type, SwingComponent component) {
        JComponent parentsComponent = component.getParentsComponent();
        tabbedPane.addTab(type, parentsComponent);
    }

    private SwingComponent createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingComponent createPanel(String type, Visitor visitor) {
                return createNewPanel(type);
            }
        };
        return abstractPanel.order(type, visitor);
    }

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
    public void acceptVisitor(Visitor visitor) {}

    @Override
    public JComponent getParentsComponent() {
        return tabbedPane;
    }
}
