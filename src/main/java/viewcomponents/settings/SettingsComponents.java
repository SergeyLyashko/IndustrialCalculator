package viewcomponents.settings;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import view.ViewController;
import view.AppComponent;
import view.CalculatorComponents;
import view.WinCloseObserver;

import java.util.ArrayList;
import java.util.List;

@Service("settingsComponents")
public class SettingsComponents implements CalculatorComponents, WinCloseObserver, InitializingBean {

    private List<AppComponent> components;
    private ViewController viewController;

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
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

    public SettingsComponents(/*ViewController viewController*/) {
        /*this.viewController = viewController;
        List<AppComponent> saved = viewController.loadComponents();
        if(saved == null){
            components = new ArrayList<>();
            integration(new ColorThemeCheckBox());
            integration(new ToolTipsCheckBox());
        }else {
            saved.forEach(component -> component.addController(viewController));
            components = saved;
        }*/
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
