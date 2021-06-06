package ui.calculator;

import controller.FieldsAction;
import controller.LabelBehavior;
import controller.ViewController;
import database.MenuListProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ui.ColorChanger;
import ui.UiComponent;
import ui.CalculatorComponents;
import ui.MenuSelectable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
public class CalculatorUiConfiguration {

    @Autowired
    private ViewController viewController;
    @Autowired
    private MenuListProducer menuListProducer;
    @Autowired
    @Qualifier("widthAction")
    private FieldsAction widthAction;
    @Autowired
    @Qualifier("lengthAction")
    private FieldsAction lengthAction;
    @Autowired
    private ColorChanger colorChanger;
    @Autowired
    @Qualifier("messageBehavior")
    private LabelBehavior messageBehavior;
    @Autowired
    @Qualifier("resultBehavior")
    private LabelBehavior resultBehavior;

    @Bean(name = "width")
    public UiComponent width(){
        String description = "введите ширину";
        String toolTip = "поле ввода ширины детали";
        FieldValue fieldValue = new FieldValue(description, toolTip, 190, 20, FieldValue.FocusRate.FOURTH);
        FieldValue.Type.WIDTH.setActionComponent(fieldValue, viewController, widthAction);
        return fieldValue;
    }

    @Bean(name = "length")
    public UiComponent length(){
        String description = "введите длину";
        String toolTip = "поле ввода длины детали";
        FieldValue fieldValue = new FieldValue(description, toolTip, 190, 60, FieldValue.FocusRate.FIFTH);
        FieldValue.Type.LENGTH.setActionComponent(fieldValue, viewController, lengthAction);
        return fieldValue;
    }

    @Bean
    public UiComponent dimensionWidth(){
        String defaultText = "mm";
        TextLabel textLabel = new TextLabel(defaultText, 320, 22, 25, 20, SwingConstants.RIGHT);
        TextLabel.Type.DIMENSION_WIDTH.addColorChanger(textLabel, colorChanger);
        return textLabel;
    }

    @Bean
    public UiComponent dimensionLength(){
        String defaultText = "mm";
        TextLabel textLabel = new TextLabel(defaultText, 320, 62, 25, 20, SwingConstants.RIGHT);
        TextLabel.Type.DIMENSION_LENGTH.addColorChanger(textLabel, colorChanger);
        return textLabel;
    }

    @Bean
    public UiComponent message(){
        String defaultText = "";
        TextLabel textLabel = new TextLabel(defaultText, 20, 140, 315, 15, SwingConstants.CENTER);
        TextLabel.Type.MESSAGE.addColorChanger(textLabel, colorChanger);
        TextLabel.Type.MESSAGE.addBehavior(textLabel, messageBehavior);
        return textLabel;
    }

    @Bean
    public UiComponent result(){
        String defaultText = "0.0 кг";
        TextLabel textLabel = new TextLabel(defaultText, 190, 105, 125, 25, SwingConstants.RIGHT);
        TextLabel.Type.RESULT.addColorChanger(textLabel, colorChanger);
        TextLabel.Type.RESULT.addBehavior(textLabel, resultBehavior);
        return textLabel;
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
