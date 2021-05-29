package ui.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.UiComponent;
import ui.CalculatorComponents;
import ui.Visitor;

@Configuration
public class CalculatorSettingsConfiguration {

    @Bean(name = "Настройки компоненты")
    public CalculatorComponents settingsComponents(){
        return new SettingsComponents();
    }
    /*
    @Bean
    public UiComponent colorThemeBox(){
        return new ColorThemeCheckBox(15, 35);
    }

    @Bean
    public UiComponent toolTipsBox(){
        return new ToolTipsCheckBox(15, 60);
    }*/

    @Bean
    public Visitor colorVisitor(){
        return new ColorVisitor();
    }

    @Bean(name = "colorThemeBox")
    public UiComponent colorThemeBox(){
        String boxTitle = "темная тема оформления";
        String toolTipText = "включить/отключить темную тему приложения";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 15, 35, colorVisitor());
        CheckBox.TypeBox.COLOR_THEME.addItemListener(checkBox, colorVisitor());
        return checkBox;
    }

    @Bean(name = "toolTipsBox")
    public UiComponent toolTipsBox(){
        String boxTitle = "включить всплывающие подсказки";
        String toolTipText = "включение/отключение всплывающих подсказок";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 15, 60, colorVisitor());
        CheckBox.TypeBox.TOOL_TIPS.addItemListener(checkBox, colorVisitor());
        return checkBox;
    }

    @Bean(name = "areaCheckBox")
    public UiComponent areaCheckBox(){
        String boxTitle = "сложный периметр";
        String toolTipText = "расчет массы детали по задаваемой площади детали";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 187, 85, colorVisitor());
        checkBox.setFont(checkBox.getFont().deriveFont(10f));
        CheckBox.TypeBox.AREA.addItemListener(checkBox, colorVisitor());
        return checkBox;
    }
}
