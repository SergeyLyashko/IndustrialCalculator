package configurations;

import calculators.DetailConfiguration;
import controller.impl.ViewControllerConfiguration;
import database.services.DatabaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfiguration.class, DetailConfiguration.class, ViewControllerConfiguration.class})
public class CalculatorConfiguration {

}
