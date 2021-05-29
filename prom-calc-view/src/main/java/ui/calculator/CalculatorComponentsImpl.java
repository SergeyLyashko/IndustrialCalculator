package ui.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ui.AppComponent;
import ui.CalculatorComponents;
import ui.MenuSelectable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorComponentsImpl implements CalculatorComponents {

    private final List<AppComponent> components;
    @Autowired
    @Qualifier("assortmentsMenu")
    private MenuSelectable assortment;

    @Autowired
    @Qualifier("typesMenu")
    private MenuSelectable types;

    @Autowired
    @Qualifier("numbersMenu")
    private MenuSelectable numbers;

    @Autowired
    @Qualifier("width")
    private AppComponent width;

    @Autowired
    @Qualifier("length")
    private AppComponent length;

    @Autowired
    @Qualifier("areaCheckBox")
    private AppComponent areaCheckBox;

    @Autowired
    @Qualifier("result")
    private AppComponent result;

    @Autowired
    @Qualifier("message")
    private AppComponent message;

    @Autowired
    @Qualifier("dimensionWidth")
    private AppComponent dimensionWidth;

    @Autowired
    @Qualifier("dimensionLength")
    private AppComponent dimensionLength;

    public CalculatorComponentsImpl() {
        components = new ArrayList<>();
    }

    @PostConstruct
    private void afterPropertiesSet() {
        components.add(width);
        components.add(length);
        components.add(areaCheckBox);
        components.add(assortment);
        components.add(types);
        components.add(numbers);
        components.add(result);
        components.add(message);
        components.add(dimensionWidth);
        components.add(dimensionLength);

        addListeners(assortment, types, numbers);
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }

    private void addListeners(MenuSelectable...menus) {
        MenuSelectable assortment = menus[0];
        MenuSelectable types = menus[1];
        MenuSelectable numbers = menus[2];
        assortment.addMenuSelectListener(types);
        assortment.addMenuSelectListener(numbers);
        types.addMenuSelectListener(numbers);
    }
}
