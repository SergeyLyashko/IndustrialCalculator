package view.settings;

import view.*;
import view.settings.checkboxes.ColorThemeCheckBox;
import view.settings.checkboxes.ToolTipsCheckBox;

import java.util.ArrayList;
import java.util.List;

public class SettingsFactory implements ComponentsFactory {

    private final List<AppComponent> componentList = new ArrayList<>();

    private void integration(AppComponent component, Visitor visitor) {
        component.integration();
        component.registerHost(visitor);
        componentList.add(component);
    }

    @Override
    public List<AppComponent> getComponentList() {
        return componentList;
    }

    @Override
    public void create(MenuReceivable menuReceivable, Visitor visitor) {
        integration(new ColorThemeCheckBox(), visitor);
        integration(new ToolTipsCheckBox(), visitor);
    }
}
