package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewControllerConfiguration {

    @Bean
    public ViewController viewController(){
        return new ViewControllerImpl();
    }

    @Bean
    public FieldsAction widthAction(){
        return new FieldsAction();
    }

    @Bean
    public FieldsAction lengthAction(){
        return new FieldsAction();
    }
}
