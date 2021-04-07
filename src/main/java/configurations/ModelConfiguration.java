package configurations;

import controller.DataValueParser;
import model.DataValueParserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;

@Configuration
public class ModelConfiguration {

    @Bean
    public DecimalFormat decimalFormat(){
        return new DecimalFormat("#.###");
    }

    @Bean
    public DataValueParser dataValueParser(){
        return new DataValueParserImpl();
    }
}
