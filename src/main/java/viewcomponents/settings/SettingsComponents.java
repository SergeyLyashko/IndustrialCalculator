package viewcomponents.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import view.ViewController;
import view.AppComponent;
import view.CalculatorComponents;
import view.WinCloseObserver;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("settingsComponents")
public class SettingsComponents implements CalculatorComponents, WinCloseObserver {

    private List<AppComponent> components;
    private ViewController viewController;
    private AppComponent colorThemeCheckBox;
    private AppComponent toolTipsCheckBox;

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @Autowired
    @Qualifier("colorThemeBox")
    public void setColorThemeCheckBox(AppComponent colorThemeCheckBox){
        this.colorThemeCheckBox = colorThemeCheckBox;
    }

    @Autowired
    @Qualifier("toolTipsBox")
    public void setToolTipsCheckBox(AppComponent toolTipsCheckBox){
        this.toolTipsCheckBox = toolTipsCheckBox;
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        List<AppComponent> saved = viewController.loadComponents();
        if(saved == null){
            components = new ArrayList<>();
            integration(colorThemeCheckBox);
            integration(toolTipsCheckBox);
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
