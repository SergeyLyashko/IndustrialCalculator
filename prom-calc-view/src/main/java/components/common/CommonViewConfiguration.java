package components.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import configurations.ViewCalculatorConfiguration;
import components.info.ViewInfoConfiguration;
import components.settings.ViewSettingsConfiguration;
import model.ViewModelConfiguration;

@Configuration
@Import({ViewCalculatorConfiguration.class, ViewSettingsConfiguration.class, ViewModelConfiguration.class,
        ViewInfoConfiguration.class})
public class CommonViewConfiguration {

    @Bean
    public CalculatorFocusTraversalPolicy focusTraversalPolicy(){
        return new CalculatorFocusTraversalPolicy();
    }

    @Bean(name = "Калькулятор")
    public AppPanel calculatorPanel(){
        return new AppPanel();
    }

    @Bean(name = "Настройки")
    public AppPanel settingsPanel(){
        return new AppPanel();
    }

    @Bean(name = "Справка")
    public AppPanel infoPanel(){
        return new AppPanel();
    }

    @Bean
    public AppFrame appFrame(){
        return new AppFrame();
    }
}
