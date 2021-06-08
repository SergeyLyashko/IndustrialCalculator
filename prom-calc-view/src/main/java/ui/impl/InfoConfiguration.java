package ui.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.UiComponent;
import ui.PanelComponents;

import java.util.ArrayList;
import java.util.List;

@Configuration("Справка конфигурация")
public class InfoConfiguration implements PanelComponents {

    private final List<UiComponent> panelComponents = new ArrayList<>();

    @Bean
    public UiComponent scroller(){
        ScrollWrapper scrollWrapper = new ScrollWrapper();
        panelComponents.add(scrollWrapper);
        return scrollWrapper;
    }

    @Bean
    public UiComponent info(){
        return new Info();
    }

    @Override
    public List<UiComponent> getPanelComponents(){
        return panelComponents;
    }
}
