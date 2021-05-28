package configurations;

import components.common.AppFrame;
import components.common.AppPanel;
import components.common.CalculatorFocusTraversalPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
