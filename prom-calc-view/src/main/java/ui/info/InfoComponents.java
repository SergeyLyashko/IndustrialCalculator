package ui.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ui.UiComponent;
import ui.CalculatorComponents;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("infoComponents")
public class InfoComponents implements CalculatorComponents {

    private List<UiComponent> components;

    @Autowired
    @Qualifier("scroller")
    private UiComponent scrollWrapper;

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        components = new ArrayList<>();
        components.add(scrollWrapper);
    }

    @Override
    public List<UiComponent> getComponents(){
        return components;
    }
}
