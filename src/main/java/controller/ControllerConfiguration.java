package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import viewcomponents.common.ViewController;
import viewcontroller.CalculatorController;
import viewcontroller.FieldsAction;
import viewcontroller.ViewControllerImpl;

@Configuration
public class ControllerConfiguration {

    @Bean
    public ViewController viewController(){
        return new ViewControllerImpl();
    }

    @Bean
    public CalculatorController calculatorController(){
        return new CalculatorControllerImpl();
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
