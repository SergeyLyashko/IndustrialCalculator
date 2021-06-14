package ui.impl;

import controller.FieldAction;
import model.DataManager;
import model.KeyActionObserver;
import model.LabelBehavior;
import controller.ViewController;
import database.MenuListProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import ui.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Configuration("Калькулятор конфигурация")
public class CalculatorConfiguration implements PanelComponents {

    private final List<UiComponent> panelComponents = new ArrayList<>();
    private final List<FocusPolicy> focusableComponents = new ArrayList<>();

    @Autowired
    private ViewController viewController;
    @Autowired
    private MenuListProducer menuListProducer;
    @Autowired
    @Qualifier("widthAction")
    private FieldAction widthAction;
    @Autowired
    @Qualifier("lengthAction")
    private FieldAction lengthAction;
    @Autowired
    private ColorChanger colorChanger;
    @Autowired
    @Qualifier("messageBehavior")
    private LabelBehavior messageBehavior;
    @Lazy
    @Autowired
    @Qualifier("resultBehavior")
    private LabelBehavior resultBehavior;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private KeyActionObserver observer;

    @PostConstruct
    private void dataInit(){
        dataManager.setAssortment("Тип сортамента");
        dataManager.setType("Тип профиля");
        dataManager.setNumber("№ профиля");
    }

    @Bean(name = "areaCheckBox")
    public UiComponent areaCheckBox(){
        String boxTitle = "сложный периметр";
        String toolTipText = "расчет массы детали по задаваемой площади детали";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 187, 85, colorChanger);
        checkBox.setFont(checkBox.getFont().deriveFont(10f));
        CheckBox.TypeBox.AREA.addItemListener(checkBox, viewController, colorChanger, dataManager);
        panelComponents.add(checkBox);
        return checkBox;
    }

    @Bean(name = "width")
    public UiComponent width(){
        String description = "введите ширину";
        String toolTip = "поле ввода ширины детали";
        FieldValue fieldValue = new FieldValue(description, toolTip, 190, 20, FieldValue.FocusRate.FOURTH);
        FieldValue.Type.WIDTH.setActionComponent(fieldValue, viewController, widthAction);
        FieldValue.Type.WIDTH.fieldActivate(fieldValue, dataManager, observer);
        panelComponents.add(fieldValue);
        focusableComponents.add(fieldValue);
        return fieldValue;
    }

    @Bean(name = "length")
    public UiComponent length(){
        String description = "введите длину";
        String toolTip = "поле ввода длины детали";
        FieldValue fieldValue = new FieldValue(description, toolTip, 190, 60, FieldValue.FocusRate.FIFTH);
        FieldValue.Type.LENGTH.setActionComponent(fieldValue, viewController, lengthAction);
        FieldValue.Type.LENGTH.fieldActivate(fieldValue, dataManager, observer);
        panelComponents.add(fieldValue);
        focusableComponents.add(fieldValue);
        return fieldValue;
    }

    @Bean
    public UiComponent dimensionWidth(){
        String defaultText = "mm";
        TextLabel textLabel = new TextLabel(defaultText, 320, 22, 25, 20, SwingConstants.RIGHT);
        TextLabel.Type.DIMENSION_WIDTH.addColorChanger(textLabel, colorChanger);
        panelComponents.add(textLabel);
        return textLabel;
    }

    @Bean
    public UiComponent dimensionLength(){
        String defaultText = "mm";
        TextLabel textLabel = new TextLabel(defaultText, 320, 62, 25, 20, SwingConstants.RIGHT);
        TextLabel.Type.DIMENSION_LENGTH.addColorChanger(textLabel, colorChanger);
        panelComponents.add(textLabel);
        return textLabel;
    }

    @Bean
    public UiComponent message(){
        String defaultText = "";
        TextLabel textLabel = new TextLabel(defaultText, 20, 140, 315, 15, SwingConstants.CENTER);
        TextLabel.Type.MESSAGE.addColorChanger(textLabel, colorChanger);
        TextLabel.Type.MESSAGE.addBehavior(textLabel, messageBehavior);
        panelComponents.add(textLabel);
        return textLabel;
    }

    @Bean
    public UiComponent result(){
        String defaultText = "0.0 кг";
        TextLabel textLabel = new TextLabel(defaultText, 190, 105, 125, 25, SwingConstants.RIGHT);
        TextLabel.Type.RESULT.addColorChanger(textLabel, colorChanger);
        TextLabel.Type.RESULT.addBehavior(textLabel, resultBehavior);
        panelComponents.add(textLabel);
        focusableComponents.add(textLabel);
        return textLabel;
    }

    @Bean(name = "numbersMenu")
    public MenuSelectable numbersMenu(){
        String toolTipText = "выбор номера профиля детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 100, MenuBox.FocusRate.THIRD);
        MenuBox.ProfileType.NUMBERS.createMenuBox(dataManager, menuListProducer, viewController, menuBox);
        panelComponents.add(menuBox);
        focusableComponents.add(menuBox);
        return menuBox;
    }

    @Bean(name = "typesMenu")
    public MenuSelectable typesMenu(){
        String toolTipText = "выбор типа профиля детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 60, MenuBox.FocusRate.SECOND);
        MenuBox.ProfileType.TYPES.createMenuBox(dataManager, menuListProducer, viewController, menuBox, numbersMenu());
        panelComponents.add(menuBox);
        focusableComponents.add(menuBox);
        return menuBox;
    }

    @Bean(name = "assortmentsMenu")
    public MenuSelectable assortmentMenu(){
        String toolTipText = "выбор сортамента детали";
        MenuBox menuBox = new MenuBox(toolTipText, 20, 20, MenuBox.FocusRate.FIRST);
        MenuBox.ProfileType.ASSORTMENTS.createMenuBox(dataManager, menuListProducer, viewController, menuBox, typesMenu(), numbersMenu());
        panelComponents.add(menuBox);
        focusableComponents.add(menuBox);
        return menuBox;
    }

    @Override
    public List<UiComponent> getPanelComponents(){
        return panelComponents;
    }

    @Override
    public List<FocusPolicy> getFocusableComponents(){
        return focusableComponents;
    }
}
