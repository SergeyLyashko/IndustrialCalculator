package view.settingscomponents;

import view.viewcontroller.*;
import view.AppComponent;
import view.ComponentsList;

import java.util.ArrayList;
import java.util.List;

public class SettingsComponentsDI implements ComponentsList {

    private final List<AppComponent> components;

    public SettingsComponentsDI(ViewController viewController) {
        components = new ArrayList<>();
        integration(new ColorThemeCheckBox(viewController));
        integration(new ToolTipsCheckBox(viewController));
    }

    private void integration(AppComponent component) {
        component.integrationToPanel();
        components.add(component);
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }
}
