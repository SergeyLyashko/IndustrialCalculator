package tabs;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.awt.*;
import java.util.List;

class PanelFactory {

    private PanelBuilder panelBuilder;

    SwingComponent createPanel(String type, List<SwingComponent> components, Visitor visitor) throws IllegalStateException {
        panelBuilder = new PanelBuilder();
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
        panelBuilder.setName("Настройки");
        panelBuilder.setComponentsList(components);
        return panelBuilder.build(visitor);
    }

    private SwingComponent createInfoPanel(List<SwingComponent> components, Visitor visitor) {
        panelBuilder.setLayout(new BorderLayout());
        panelBuilder.setBorderLayout(BorderLayout.CENTER);
        panelBuilder.setName("Справка");
        panelBuilder.setComponentsList(components);
        return panelBuilder.build(visitor);
    }

    private SwingComponent createCalculatorPanel(List<SwingComponent> components, Visitor visitor){
        panelBuilder.setName("Калькулятор");
        panelBuilder.setComponentsList(components);
        return panelBuilder.build(visitor);
    }
}
