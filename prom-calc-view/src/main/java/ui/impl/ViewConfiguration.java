package ui.impl;

import model.impl.ViewModelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.swing.*;

@Configuration
@Import({ViewModelConfiguration.class, CalculatorConfiguration.class, SettingsConfiguration.class,  InfoConfiguration.class, })
public class ViewConfiguration {

    @Bean
    public CalculatorFocusTraversalPolicy focusTraversalPolicy(){
        return new CalculatorFocusTraversalPolicy();
    }

    @Bean(name = "Калькулятор")
    public JPanel calculatorPanel(){
        return new AppPanel();
    }

    @Bean(name = "Настройки")
    public JPanel settingsPanel(){
        return new AppPanel();
    }

    @Bean(name = "Справка")
    public JPanel infoPanel(){
        return new AppPanel();
    }

    @Bean
    public AppFrame appFrame(){
        return new AppFrame();
    }
}
