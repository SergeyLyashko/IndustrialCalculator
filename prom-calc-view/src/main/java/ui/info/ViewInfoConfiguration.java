package ui.info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.UiComponent;
import ui.CalculatorComponents;

@Configuration
public class ViewInfoConfiguration {

    @Bean(name = "Справка компоненты")
    public CalculatorComponents infoComponents(){
        return new InfoComponents();
    }

    @Bean
    public UiComponent scroller(){
        return new ScrollWrapper();
    }

    @Bean
    public UiComponent info(){
        return new Info();
    }
}
