package configurations;

import controller.CalculatorControllerImpl;
import controller.CalculatorModel;
import database.DetailsDAO;
import model.CalculatorModelImpl;
import model.CalculatorView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import view.*;
import viewcontroller.CalculatorController;

@Configuration
@Import(ViewConfiguration.class)
public class CalculatorConfiguration {

    @Bean
    public DataReceiver dataReceiver(){
        return new DetailsDAO();
    }

    @Bean
    public CalculatorView calculatorView(){
        return new CalculatorViewImpl();
    }

    @Bean
    public CalculatorModel calculatorModel(){
        return new CalculatorModelImpl();
    }

    @Bean
    public CalculatorController calculatorController(){
        return new CalculatorControllerImpl();
    }
}
