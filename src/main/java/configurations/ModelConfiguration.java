package configurations;

import controller.FieldsParser;
import model.FieldsValueParserImpl;
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
    public FieldsParser fieldsParser(){
        return new FieldsValueParserImpl();
    }
}
