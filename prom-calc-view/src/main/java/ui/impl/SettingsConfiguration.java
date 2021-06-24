package ui.impl;

import controller.ViewController;
import model.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.PanelComponents;
import ui.ColorChanger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Configuration("Настройки конфигурация")
public class SettingsConfiguration implements PanelComponents {

    private final List<JComponent> panelComponents = new ArrayList<>();

    @Autowired
    private ViewController viewController;
    @Autowired
    private DataManager data;

    @Bean
    public ColorChanger colorChanger(){
        return new ColorChangerImpl();
    }

    @Bean(name = "colorThemeBox")
    public JComponent colorThemeBox(){
        String boxTitle = "темная тема оформления";
        String toolTipText = "включить/отключить темную тему приложения";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 15, 35, colorChanger());
        CheckBox.TypeBox.COLOR_THEME.addItemListener(checkBox, viewController, colorChanger(), data);
        panelComponents.add(checkBox);
        return checkBox;
    }

    @Bean(name = "toolTipsBox")
    public JComponent toolTipsBox(){
        String boxTitle = "включить всплывающие подсказки";
        String toolTipText = "включение/отключение всплывающих подсказок";
        CheckBox checkBox = new CheckBox(boxTitle, toolTipText, 15, 60, colorChanger());
        CheckBox.TypeBox.TOOL_TIPS.addItemListener(checkBox, viewController, colorChanger(), data);
        panelComponents.add(checkBox);
        return checkBox;
    }

    @Override
    public List<JComponent> getPanelComponents(){
        return panelComponents;
    }
}
