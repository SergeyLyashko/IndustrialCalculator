package appview;

import info.InfoComponents;
import settings.SettingsComponents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PanelFactory {

    private PanelBuilder panelBuilder;

    public List<SwingComponent> getPanelsList(Visitor visitor){
        SwingComponent calc = createContent("Калькулятор", visitor);
        SwingComponent settings = createContent("Настройки", visitor);
        SwingComponent info = createContent("Справка", visitor);
        return Stream.of(calc, settings, info).collect(Collectors.toList());
    }

    private SwingComponent createContent(String type, Visitor visitor) throws IllegalStateException {
        panelBuilder = new PanelBuilder();
        switch (type){
            case "Калькулятор":
                return createCalculatorPanel(visitor);
            case "Настройки":
                return createSettingsPanel(visitor);
            case "Справка":
                return createInfoPanel(visitor);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private SwingComponent createSettingsPanel(Visitor visitor){
        panelBuilder.setName("Настройки");
        List<SwingComponent> settingsComponents = createSettingsComponents(visitor);
        panelBuilder.setComponentsList(settingsComponents);
        return panelBuilder.build(visitor);
    }

    private List<SwingComponent> createSettingsComponents(Visitor visitor){
        SettingsComponents settingsComponent = new SettingsComponents();
        return settingsComponent.getComponents(visitor);
    }

    private SwingComponent createInfoPanel(Visitor visitor) {
        panelBuilder.setLayout(new BorderLayout());
        panelBuilder.setBorderLayout(BorderLayout.CENTER);
        panelBuilder.setName("Справка");
        List<SwingComponent> infoComponents = createInfoComponents(visitor);
        panelBuilder.setComponentsList(infoComponents);
        return panelBuilder.build(visitor);
    }

    private List<SwingComponent> createInfoComponents(Visitor visitor){
        List<SwingComponent> components = new ArrayList<>();
        InfoComponents infoComponent = new InfoComponents();
        SwingComponent info = infoComponent.getComponents(visitor);
        components.add(info);
        return components;
    }

    private SwingComponent createCalculatorPanel(Visitor visitor){
        panelBuilder.setName("Калькулятор");
        return panelBuilder.build(visitor);
    }
}
