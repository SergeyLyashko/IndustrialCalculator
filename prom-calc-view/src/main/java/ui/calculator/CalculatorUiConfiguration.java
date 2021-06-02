package ui.calculator;

import controller.ViewController;
import database.DataReceiver;
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
    private DataReceiver dataReceiver;

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

    /*
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
    */

    @Bean(name = "numbersMenu")
    public MenuSelectable numbersMenu(){
        String toolTipText = "выбор номера профиля детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 100, 3);
        MenuBox.ProfileType.NUMBERS.receiveMenu(dataReceiver, viewController, menuBox);
        //MenuBox.ProfileType.NUMBERS.addActionListener();
        return menuBox;
    }

    @Bean(name = "typesMenu")
    public MenuSelectable typesMenu(){
        String toolTipText = "выбор типа профиля детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 60, 2);
        MenuBox.ProfileType.TYPES.receiveMenu(dataReceiver, viewController, menuBox, numbersMenu());
        //MenuBox.ProfileType.TYPES.addActionListener(dataReceiver, viewController, menuBox, numbersMenu());
        return menuBox;
    }

    @Bean(name = "assortmentsMenu")
    public MenuSelectable assortmentMenu(){
        String toolTipText = "выбор сортамента детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 20, 1);
        MenuBox.ProfileType.ASSORTMENTS.receiveMenu(dataReceiver, viewController, menuBox, typesMenu(), numbersMenu());
        //MenuBox.ProfileType.ASSORTMENTS.addActionListener(dataReceiver, viewController, menuBox, typesMenu(), numbersMenu());
        return menuBox;
    }

    @Bean(name = "Калькулятор компоненты")
    public CalculatorComponents calculatorComponents(){
        return new CalculatorComponentsImpl();
    }
}
