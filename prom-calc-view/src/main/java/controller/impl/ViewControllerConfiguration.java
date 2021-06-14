package controller.impl;

import controller.CalculatorController;
import controller.FieldAction;
import controller.ViewController;
import model.impl.ViewModelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ui.impl.ViewConfiguration;

@Configuration
@Import({ ViewModelConfiguration.class, ViewConfiguration.class})
public class ViewControllerConfiguration {

    @Bean
    public CalculatorController calculatorController(){
        return new CalculatorControllerImpl();
    }

    @Bean
    public ViewController viewController(){
        return new ViewControllerImpl();
    }

    @Bean
    public FieldAction widthAction(){
        return new FieldActionImpl();
    }

    @Bean
    public FieldAction lengthAction(){
        return new FieldActionImpl();
    }
}
