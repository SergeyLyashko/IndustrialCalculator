package configurations;

import database.DetailsDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import view.DataReceiver;
import view.ViewController;
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

}
