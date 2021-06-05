package ui.calculator;

import controller.ViewController;
import database.MenuListProducer;
import org.springframework.beans.factory.annotation.Autowired;
import ui.UiComponent;
import ui.CalculatorComponents;
import ui.MenuSelectable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorUiConfiguration {

    @Autowired
    private ViewController viewController;
    @Autowired
    private MenuListProducer menuListProducer;

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

    @Bean(name = "numbersMenu")
    public MenuSelectable numbersMenu(){
        String toolTipText = "выбор номера профиля детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 100, MenuBox.FocusRate.THIRD);
        MenuBox.ProfileType.NUMBERS.createMenuBox(menuListProducer, viewController, menuBox);
        return menuBox;
    }

    @Bean(name = "typesMenu")
    public MenuSelectable typesMenu(){
        String toolTipText = "выбор типа профиля детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 60, MenuBox.FocusRate.SECOND);
        MenuBox.ProfileType.TYPES.createMenuBox(menuListProducer, viewController, menuBox, numbersMenu());
        return menuBox;
    }

    @Bean(name = "assortmentsMenu")
    public MenuSelectable assortmentMenu(){
        String toolTipText = "выбор сортамента детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 20, MenuBox.FocusRate.FIRST);
        MenuBox.ProfileType.ASSORTMENTS.createMenuBox(menuListProducer, viewController, menuBox, typesMenu(), numbersMenu());
        return menuBox;
    }

    @Bean(name = "Калькулятор компоненты")
    public CalculatorComponents calculatorComponents(){
        return new CalculatorComponentsImpl();
    }
}
