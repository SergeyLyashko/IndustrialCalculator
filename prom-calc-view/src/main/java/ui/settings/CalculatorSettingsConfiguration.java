package ui.settings;

import controller.ViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.UiComponent;
import ui.CalculatorComponents;
import ui.Visitor;

@Configuration
public class CalculatorSettingsConfiguration {

    @Autowired
    private ViewController viewController;

    @Bean(name = "Настройки компоненты")
    public CalculatorComponents settingsComponents(){
        return new SettingsComponents();
    }

    @Bean
    public Visitor colorVisitor(){
        return new ColorVisitor();
    }

    @Bean(name = "colorThemeBox")
    public UiComponent colorThemeBox(){
        String boxTitle = "темная тема оформления";
        String toolTipText = "включить/отключить темную тему приложения";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 15, 35, colorVisitor());
        CheckBox.TypeBox.COLOR_THEME.addItemListener(checkBox, viewController, colorVisitor());
        return checkBox;
    }

    @Bean(name = "toolTipsBox")
    public UiComponent toolTipsBox(){
        String boxTitle = "включить всплывающие подсказки";
        String toolTipText = "включение/отключение всплывающих подсказок";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 15, 60, colorVisitor());
        CheckBox.TypeBox.TOOL_TIPS.addItemListener(checkBox, viewController, colorVisitor());
        return checkBox;
    }

    @Bean(name = "areaCheckBox")
    public UiComponent areaCheckBox(){
        String boxTitle = "сложный периметр";
        String toolTipText = "расчет массы детали по задаваемой площади детали";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 187, 85, colorVisitor());
        checkBox.setFont(checkBox.getFont().deriveFont(10f));
        CheckBox.TypeBox.AREA.addItemListener(checkBox, viewController, colorVisitor());
        return checkBox;
    }
}
