package configurations;

import calculators.DetailConfiguration;
import controller.ControllerConfiguration;
import database.DatabaseConfiguration;
import model.ModelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import view.*;

@ComponentScan(basePackages = {"viewcontroller"})
@Configuration
@Import({ViewConfiguration.class, ModelConfiguration.class, DetailConfiguration.class, DatabaseConfiguration.class,
        ControllerConfiguration.class})
public class CalculatorConfiguration {

}
