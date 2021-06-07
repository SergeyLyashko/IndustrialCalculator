package ui.info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.UiComponent;
import ui.PanelComponents;

import java.util.ArrayList;
import java.util.List;

@Configuration("Справка конфигурация")
public class InfoConfiguration implements PanelComponents {

    private final List<UiComponent> panelComponents = new ArrayList<>();

    /*
    @Bean(name = "Справка компоненты")
    public CalculatorComponents infoComponents(){
        return new InfoComponents();
    }*/

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
