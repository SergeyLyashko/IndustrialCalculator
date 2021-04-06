package configurations;

import database.DetailsDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import view.CalculatorComponents;
import view.DataReceiver;
import view.ViewController;
import viewcomponents.calculator.CalculatorComponentsImpl;
import viewcontroller.ViewControllerImpl;

@ComponentScan(basePackages = {"viewcomponents.calculator"})
@Configuration
public class CalculatorConfiguration {

    @Bean
    public DataReceiver dataReceiver(){
        return new DetailsDAO();
    }

    @Bean
    public ViewController viewController(){
        return new ViewControllerImpl();
    }

    @Bean
    public CalculatorComponents calculatorComponents(){
        return new CalculatorComponentsImpl();
    }

}
