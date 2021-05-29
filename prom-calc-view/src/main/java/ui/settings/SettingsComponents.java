package ui.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import controller.ViewController;
import ui.AppComponent;
import ui.CalculatorComponents;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service("settingsComponents")
public class SettingsComponents implements CalculatorComponents {

    private List<AppComponent> components;

    @Autowired
    private ViewController viewController;

    @Autowired
    @Qualifier("colorThemeBox")
    private AppComponent colorThemeCheckBox;

    @Autowired
    @Qualifier("toolTipsBox")
    private AppComponent toolTipsCheckBox;

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
