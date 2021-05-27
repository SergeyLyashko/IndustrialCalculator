package model;

import controller.CalculatorModel;
import controller.FieldsParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;

@Configuration
public class ModelConfiguration {

    @Bean
    public CalculatorModel calculatorModel(){
        return new CalculatorModelImpl();
    }

    @Bean
    public DecimalFormat decimalFormat(){
        return new DecimalFormat("#.###");
    }

    @Bean
    public FieldsParser fieldsParser(){
        return new FieldsValueParserImpl();
    }

}
