package view.settings;

import view.*;
import view.settings.checkboxes.ColorThemeCheckBox;
import view.settings.checkboxes.ToolTipsCheckBox;

import java.util.ArrayList;
import java.util.List;

public class SettingsFactory implements ComponentsFactory {

    private final List<AppComponent> components = new ArrayList<>();

    private void integration(AppComponent swingComponent, Visitor visitor) {
        swingComponent.integration(visitor);
        components.add(swingComponent);
    }

    @Override
    public List<AppComponent> getComponents() {
        return components;
    }

    @Override
    public void create(ReceivableMenu receivableMenu, Visitor visitor) {
        integration(new ColorThemeCheckBox(), visitor);
        integration(new ToolTipsCheckBox(), visitor);
    }
}
