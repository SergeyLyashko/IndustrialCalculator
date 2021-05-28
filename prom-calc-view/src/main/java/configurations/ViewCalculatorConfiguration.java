package configurations;

import components.calculator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import components.common.AppComponent;
import components.common.CalculatorComponents;
import components.common.MenuSelectable;

@Configuration
public class ViewCalculatorConfiguration {

    @Bean(name = "Калькулятор компоненты")
    public CalculatorComponents calculatorComponents(){
        return new CalculatorComponentsImpl();
    }

    @Bean
    public AppComponent areaCheckBox(){
        return new AreaCheckBox(187, 85);
    }

    @Bean
    public AppComponent length(){
        return new Length(190, 60);
    }

    @Bean
    public AppComponent dimensionWidth(){
        return new DimensionLabel(320, 22);
    }

    @Bean
    public AppComponent dimensionLength(){
        return new DimensionLabel(320, 62);
    }

    @Bean
    public AppComponent message(){
        return new Message(20,140);
    }

    @Bean
    public AppComponent result(){
        return new Result(190, 105);
    }

    @Bean
    public AppComponent width(){
        return new Width(190, 20);
    }

    @Bean
    public MenuSelectable numbersMenu(){
        return new NumbersMenu(20, 100);
    }

    @Bean
    public MenuSelectable assortmentsMenu(){
        return new AssortmentsMenu(20, 20);
    }

    @Bean
    public MenuSelectable typesMenu(){
        return new TypesMenu(20, 60);
    }
}
