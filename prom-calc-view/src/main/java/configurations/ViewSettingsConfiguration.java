package configurations;

import components.settings.ColorThemeCheckBox;
import components.settings.SettingsComponents;
import components.settings.ToolTipsCheckBox;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import components.common.AppComponent;
import components.common.CalculatorComponents;

@Configuration
public class ViewSettingsConfiguration {

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
