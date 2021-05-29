package configurations;

import ui.info.Info;
import ui.info.InfoComponents;
import ui.info.ScrollWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.AppComponent;
import ui.CalculatorComponents;

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
