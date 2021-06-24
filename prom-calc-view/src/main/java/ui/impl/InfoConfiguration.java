package ui.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.PanelComponents;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Configuration("Справка конфигурация")
public class InfoConfiguration implements PanelComponents {

    private final List<JComponent> panelComponents = new ArrayList<>();

    @Bean
    public JComponent scroller(){
        ScrollWrapper scrollWrapper = new ScrollWrapper();
        panelComponents.add(scrollWrapper);
        return scrollWrapper;
    }

    @Bean
    public JComponent info(){
        return new Info();
    }

    @Override
    public List<JComponent> getPanelComponents(){
        return panelComponents;
    }
}
