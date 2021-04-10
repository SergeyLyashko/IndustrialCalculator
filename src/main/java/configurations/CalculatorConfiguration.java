package configurations;

import controller.CalculatorControllerImpl;
import controller.CalculatorModel;
import database.DatabaseConnector;
import database.DetailsDAO;
import database.DatabaseExecutor;
import model.CalculatorModelImpl;
import model.CalculatorView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import view.*;
import viewcontroller.CalculatorController;

@Configuration
@Import({ViewConfiguration.class, ModelConfiguration.class, DetailCalculatorConfiguration.class})
public class CalculatorConfiguration {

    @Bean
    public DataReceiver dataReceiver(){
        return new DetailsDAO();
    }

    @Bean
    public DatabaseExecutor executor(){
        return new DatabaseExecutor();
    }

    @Bean
    public DatabaseConnector databaseConnector(){
        return new DatabaseConnector();
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
