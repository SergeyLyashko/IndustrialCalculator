package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import view.AppComponent;
import viewcomponents.settings.ColorThemeCheckBox;
import viewcomponents.settings.ToolTipsCheckBox;

@Configuration
public class ViewSettingsConfiguration {

    @Bean
    public AppComponent colorThemeBox(){
        return new ColorThemeCheckBox(15, 35);
    }

    @Bean
    public AppComponent toolTipsBox(){
        return new ToolTipsCheckBox(15, 60);
    }
}
