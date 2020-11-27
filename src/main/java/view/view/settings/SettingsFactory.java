package view.view.settings;

import view.controller.*;
import view.view.AppComponent;
import view.view.ComponentsFactory;

import java.util.ArrayList;
import java.util.List;

public class SettingsFactory implements ComponentsFactory {

    private final List<AppComponent> componentList;
    private final ViewController viewController;

    public SettingsFactory(ViewController viewController) {
        this.viewController = viewController;
        componentList = new ArrayList<>();
    }

    @Override
    public List<AppComponent> createComponents() {
        Visitor controllerVisitor = viewController.getVisitor();
        integration(new ColorThemeCheckBox(viewController), controllerVisitor);
        integration(new ToolTipsCheckBox(viewController), controllerVisitor);
        return componentList;
    }

    private void integration(AppComponent component, Visitor visitor) {
        component.integrationToPanel();
        component.registerAsHost(visitor);
        componentList.add(component);
    }
}
