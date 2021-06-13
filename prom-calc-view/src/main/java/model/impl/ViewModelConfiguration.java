package model.impl;

import model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;

@ComponentScan(basePackages = {"model.impl"})
@Configuration
public class ViewModelConfiguration {

    @Bean
    public FieldsParser fieldsParser(){
        return new FieldsValueParserImpl();
    }

    @Bean
    public DecimalFormat decimalFormat(){
        return new DecimalFormat("#.###");
    }

    @Bean
    public Data data(){
        return new Data();
    }

    @Bean
    public ViewModel viewModel(){
        return new ViewModelImpl();
    }

    @Bean
    public Filter defaultFilter(){
        return new DefaultFilter();
    }

    @Bean
    public Filter digitalFilter(){
        return new DigitalFilter();
    }

    @Bean
    public LabelBehavior messageBehavior(){
        return new LabelBehaviorImpl();
    }

    @Bean
    public LabelBehavior resultBehavior(){
        return new LabelBehaviorImpl();
    }

    @Bean
    public FieldBehavior fieldBehavior(){
        return new FieldBehaviorImpl();
    }

    @Bean
    public FocusBehavior focusBehavior(){
        return new FocusBehaviorImpl();
    }

    /*
    @Bean
    public Properties properties(){
        return new Properties();
    }*/
}
