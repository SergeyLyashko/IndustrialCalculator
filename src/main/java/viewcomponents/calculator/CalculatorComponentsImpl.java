package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import view.AppComponent;
import view.CalculatorComponents;
import view.MenuSelectable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("calculatorComponents")
public class CalculatorComponentsImpl implements CalculatorComponents {

    private final List<AppComponent> components;
    private MenuSelectable assortment;
    private MenuSelectable types;
    private MenuSelectable numbers;
    private AppComponent width;
    private AppComponent length;
    private AppComponent areaCheckBox;
    private AppComponent result;
    private AppComponent message;
    private AppComponent dimensionWidth;
    private AppComponent dimensionLength;

    @Autowired
    @Qualifier("assortmentsMenu")
    public void setAssortment(MenuSelectable assortment){
        this.assortment = assortment;
    }

    @Autowired
    @Qualifier("typesMenu")
    public void setTypes(MenuSelectable types){
        this.types = types;
    }

    @Autowired
    @Qualifier("numbersMenu")
    public void setNumbers(MenuSelectable numbers){
        this.numbers = numbers;
    }

    @Autowired
    @Qualifier("width")
    public void setWidth(AppComponent width){
        this.width = width;
    }

    @Autowired
    @Qualifier("length")
    public void setLength(AppComponent length){
        this.length = length;
    }

    @Autowired
    @Qualifier("areaCheckBox")
    public void setAreaCheckBox(AppComponent areaCheckBox){
        this.areaCheckBox = areaCheckBox;
    }

    @Autowired
    @Qualifier("result")
    public void setResult(AppComponent result){
        this.result = result;
    }

    @Autowired
    @Qualifier("message")
    public void setMessage(AppComponent message){
        this.message = message;
    }

    @Autowired
    @Qualifier("dimensionWidth")
    public void setDimensionWidth(AppComponent dimensionWidth){
        this.dimensionWidth = dimensionWidth;
    }

    @Autowired
    @Qualifier("dimensionLength")
    public void setDimensionLength(AppComponent dimensionLength){
        this.dimensionLength = dimensionLength;
    }

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        integration(width);
        integration(length);
        integration(areaCheckBox);

        integration(assortment, types, numbers);
        addListeners(assortment, types, numbers);

        integration(result);
        integration(message);

        integration(dimensionWidth);
        integration(dimensionLength);
    }

    public CalculatorComponentsImpl() {
        components = new ArrayList<>();
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }

    private void integration(MenuSelectable...menus){
        Arrays.stream(menus).forEach(this::integration);
    }

    private void integration(AppComponent component) {
        components.add(component);
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
