package configurations;

import calculators.DetailConfiguration;
import controller.CalculatorControllerImpl;
import database.DatabaseConfiguration;
import model.CalculatorView;
import model.ModelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import view.*;
import viewcontroller.CalculatorController;

@Configuration
@Import({ViewConfiguration.class,
        ModelConfiguration.class,
        DetailConfiguration.class,
        DatabaseConfiguration.class})
public class CalculatorConfiguration {

    @Bean
    public CalculatorView calculatorView(){
        return new CalculatorViewImpl();
    }

    @Bean
    public CalculatorController calculatorController(){
        return new CalculatorControllerImpl();
    }
}
