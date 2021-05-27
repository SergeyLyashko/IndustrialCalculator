package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import viewcomponents.common.AppComponent;
import viewcomponents.common.CalculatorComponents;
import viewcomponents.common.MenuSelectable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
class CalculatorComponentsImpl implements CalculatorComponents {

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
