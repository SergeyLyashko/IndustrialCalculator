package ui.calculator;

import ui.UiComponent;
import ui.CalculatorComponents;
import ui.MenuSelectable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorUiConfiguration {

    @Bean(name = "Калькулятор компоненты")
    public CalculatorComponents calculatorComponents(){
        return new CalculatorComponentsImpl();
    }
    /*
    @Bean
    public UiComponent areaCheckBox(){
        return new AreaCheckBox(187, 85);
    }*/

    @Bean(name = "length")
    public UiComponent length(){
        return new Length(190, 60);
    }

    @Bean
    public UiComponent dimensionWidth(){
        return new DimensionLabel(320, 22);
    }

    @Bean
    public UiComponent dimensionLength(){
        return new DimensionLabel(320, 62);
    }

    @Bean
    public UiComponent message(){
        return new Message(20,140);
    }

    @Bean
    public UiComponent result(){
        return new Result(190, 105);
    }

    @Bean(name = "width")
    public UiComponent width(){
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
