package viewcomponents.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import view.ViewController;
import view.AppComponent;
import view.CalculatorComponents;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service("settingsComponents")
class SettingsComponents implements CalculatorComponents {

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
            components.add(colorThemeCheckBox);
            components.add(toolTipsCheckBox);
        }else {
            components = saved;
        }
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }

    @PreDestroy
    private void winClose() {
        System.out.println("destroy");
        // TODO написать сохранение настроек
        //viewController.savedPreference(components);
    }
}
