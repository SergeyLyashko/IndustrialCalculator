package controller.impl;

import controller.CalculatorController;
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
    public FieldsAction widthAction(){
        return new FieldsAction();
    }

    @Bean
    public FieldsAction lengthAction(){
        return new FieldsAction();
    }
}
