package configurations;

import calculators.DetailConfiguration;
import controller.ControllerConfiguration;
import database.DatabaseConfiguration;
import model.ModelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import components.common.*;

@ComponentScan(basePackages = {"viewcontroller"})
@Configuration
@Import({CommonViewConfiguration.class, ModelConfiguration.class, DetailConfiguration.class, DatabaseConfiguration.class,
        ControllerConfiguration.class})
public class CalculatorConfiguration {

}
