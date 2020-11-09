package tabs;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.awt.*;
import java.util.List;

class PanelFactory {

    private Panel panel;
    // TODO заменить LIST на ComponentsCollector ?????
    SwingComponent createPanel(String type, List<SwingComponent> components, Visitor visitor) throws IllegalStateException {
        panel = new Panel();
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
        panel.setName("Настройки");
        panel.setComponentsList(components);
        return panel.create(visitor);
    }

    private SwingComponent createInfoPanel(List<SwingComponent> components, Visitor visitor) {
        panel.setLayout(new BorderLayout());
        panel.setBorderLayout(BorderLayout.CENTER);
        panel.setName("Справка");
        panel.setComponentsList(components);
        return panel.create(visitor);
    }

    private SwingComponent createCalculatorPanel(List<SwingComponent> components, Visitor visitor){
        panel.setName("Калькулятор");
        panel.setComponentsList(components);
        return panel.create(visitor);
    }
}
