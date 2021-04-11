package viewcomponents.info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import viewcomponents.common.AppComponent;
import viewcomponents.common.CalculatorComponents;

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
