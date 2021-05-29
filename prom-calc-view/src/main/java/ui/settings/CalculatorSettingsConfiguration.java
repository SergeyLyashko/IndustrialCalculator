package ui.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.AppComponent;
import ui.CalculatorComponents;

@Configuration
public class CalculatorSettingsConfiguration {

    @Bean(name = "Настройки компоненты")
    public CalculatorComponents settingsComponents(){
        return new SettingsComponents();
    }

    @Bean
    public AppComponent colorThemeBox(){
        return new ColorThemeCheckBox(15, 35);
    }

    @Bean
    public AppComponent toolTipsBox(){
        return new ToolTipsCheckBox(15, 60);
    }
}
