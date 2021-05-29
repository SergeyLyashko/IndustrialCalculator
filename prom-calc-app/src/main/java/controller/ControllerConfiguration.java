package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public CalculatorController calculatorController(){
        return new CalculatorControllerImpl();
    }
}
