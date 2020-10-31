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
        createPanel("Калькулятор", visitor);
        createPanel("Настройки", visitor);
        createPanel("Справка", visitor);
        componentPanel.add(this);
        return componentPanel;
    }

    private void createPanel(String type, Visitor visitor){
        AbstractPanel abstractPanel = new AbstractPanel() {
            @Override
            public SwingComponent createPanel(String type, Visitor visitor) {
                return createNewPanel(type);
            }
        };
        abstractPanel.order(type, visitor);
        JPanel abstractComponent = abstractPanel.getAbstractComponent();
        tabbedPane.addTab(type, abstractComponent);
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
