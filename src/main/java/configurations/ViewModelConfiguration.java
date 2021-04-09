package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import view.Visitor;
import viewcontroller.*;
import viewmodel.*;

@Configuration
public class ViewModelConfiguration {

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

    @Bean
    public FieldBehavior widthBehavior(){
        return new FieldBehaviorImpl();
    }

    @Bean
    public FieldBehavior lengthBehavior(){
        return new FieldBehaviorImpl();
    }
}
