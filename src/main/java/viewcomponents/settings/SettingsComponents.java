package viewcomponents.settings;

import view.ViewController;
import view.AppComponent;
import view.CalculatorComponents;
import view.WinCloseObserver;

import java.util.ArrayList;
import java.util.List;

public class SettingsComponents implements CalculatorComponents, WinCloseObserver {

    private final List<AppComponent> components;
    private final ViewController viewController;

    public SettingsComponents(ViewController viewController) {
        this.viewController = viewController;
        List<AppComponent> saved = viewController.loadComponents();
        if(saved == null){
            components = new ArrayList<>();
            integration(new ColorThemeCheckBox());
            integration(new ToolTipsCheckBox());
        }else {
            saved.forEach(component -> component.addController(viewController));
            components = saved;
        }
    }

    private void integration(AppComponent component) {
        component.addController(viewController);
        component.integrationToPanel();
        components.add(component);
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }

    @Override
    public void winCloseUpdate() {
        viewController.savedPreference(components);
    }
}
