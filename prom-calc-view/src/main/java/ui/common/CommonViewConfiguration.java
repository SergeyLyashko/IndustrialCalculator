package ui.common;

import controller.ViewControllerConfiguration;
import ui.settings.CalculatorSettingsConfiguration;
import ui.info.ViewInfoConfiguration;
import ui.calculator.CalculatorUiConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import model.ViewModelConfiguration;

@Configuration
@Import({CalculatorUiConfiguration.class, CalculatorSettingsConfiguration.class, ViewControllerConfiguration.class,
        ViewModelConfiguration.class,  ViewInfoConfiguration.class})
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
