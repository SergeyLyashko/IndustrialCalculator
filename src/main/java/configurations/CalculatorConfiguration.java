package configurations;

import calculators.DetailConfiguration;
import controller.CalculatorControllerImpl;
import database.DatabaseConfiguration;
import model.ModelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import view.*;
import viewcontroller.CalculatorController;
import viewcontroller.FieldsAction;
import viewcontroller.ViewControllerImpl;

@ComponentScan(basePackages = {"viewcontroller"})
@Configuration
@Import({ViewConfiguration.class, ModelConfiguration.class, DetailConfiguration.class, DatabaseConfiguration.class})
public class CalculatorConfiguration {

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
