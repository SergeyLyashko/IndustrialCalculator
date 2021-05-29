package model;

import controller.CalculatorData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ui.Visitor;
import controller.*;

import java.util.Properties;

@ComponentScan(basePackages = {"model"})
@Configuration
public class ViewModelConfiguration {

    @Bean
    public CalculatorData calculatorData(){
        return new CalculatorDataImpl();
    }

    @Bean
    public Visitor colorVisitor(){
        return new ColorVisitor();
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
    public Properties properties(){
        return new Properties();
    }
}
