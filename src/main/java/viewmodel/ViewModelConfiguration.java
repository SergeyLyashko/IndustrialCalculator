package viewmodel;

import controller.CalculatorData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import viewcomponents.common.Visitor;
import viewcontroller.*;

@ComponentScan(basePackages = {"viewmodel"})
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
    public Preference preference(){
        return new PreferenceImpl();
    }

    @Bean
    public LabelBehavior messageBehavior(){
        return new LabelBehaviorImpl();
    }

    @Bean
    public LabelBehavior resultBehavior(){
        return new LabelBehaviorImpl();
    }
}
