package model.impl;

import model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ui.impl.ViewConfiguration;

import javax.swing.*;
@ComponentScan(basePackages = {"model.impl"})
@Configuration
public class ViewModelConfiguration {
    /*
    @Bean
    public CalculatorData calculatorData(){
        return new CalculatorDataImpl();
    }*/

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
    public KeyBehavior keyBehavior(){
        return new KeyBehaviorImpl();
    }*/

    /*
    @Bean
    public Properties properties(){
        return new Properties();
    }*/
}
