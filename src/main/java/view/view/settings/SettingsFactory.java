package view.view.settings;

import view.controller.*;
import view.view.AppComponent;
import view.view.ComponentsFactory;

import java.util.ArrayList;
import java.util.List;

public class SettingsFactory implements ComponentsFactory {

    private final List<AppComponent> componentList = new ArrayList<>();

    private void integration(AppComponent component, Visitor visitor) {
        component.integration();
        component.registerAsHost(visitor);
        componentList.add(component);
    }

    @Override
    public List<AppComponent> createComponents(ViewController viewController, Visitor visitor) {
        integration(new ColorThemeCheckBox(viewController), visitor);
        integration(new ToolTipsCheckBox(viewController), visitor);
        return componentList;
    }
}
