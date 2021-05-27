package components.info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import components.common.AppComponent;
import components.common.CalculatorComponents;

@Configuration
public class ViewInfoConfiguration {

    @Bean(name = "Справка компоненты")
    public CalculatorComponents infoComponents(){
        return new InfoComponents();
    }

    @Bean
    public AppComponent scroller(){
        return new ScrollWrapper();
    }

    @Bean
    public AppComponent info(){
        return new Info();
    }
}
