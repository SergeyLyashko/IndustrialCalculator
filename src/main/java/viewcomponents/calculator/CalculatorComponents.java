package viewcomponents.calculator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import view.AppComponent;
import view.ComponentsList;
import view.MenuSelectable;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorComponents implements ComponentsList, InitializingBean {

    private final List<AppComponent> components;
    //private DataReceiver dataReceiver;
    /*
    @Autowired
    public void setDataReceiver(DataReceiver dataReceiver){
        this.dataReceiver = dataReceiver;
    }*/
    private MenuSelectable assortment;
    private MenuSelectable types;
    private MenuSelectable numbers;
    private AppComponent width;
    private AppComponent length;
    private AppComponent areaCheckBox;

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
    @Qualifier("length")
    public void setAreaCheckBox(AppComponent areaCheckBox){
        this.areaCheckBox = areaCheckBox;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        integration(width);
        integration(length);
        integration(areaCheckBox);

        integration(assortment, types, numbers);
        addListeners(assortment, types, numbers);
    }

    public CalculatorComponents(ViewController viewController/*, DataReceiver dataReceiver*/) {
        components = new ArrayList<>();

        //integration(new Width(viewController));
        //integration(new Length(viewController));
        //integration(new AreaCheckBox(viewController));

        //MenuSelectable assortment = new AssortmentsMenu(/*viewController, dataReceiver*/);
        //MenuSelectable types = new TypesMenu(/*viewController, dataReceiver*/);
        //MenuSelectable numbers = new NumbersMenu(/*viewController, dataReceiver*/);
        //integration(assortment, types, numbers);
        //addListeners(assortment, types, numbers);

        integration(new Result(viewController));
        integration(new Message(viewController));
        integration(new DimensionLabel(viewController,320, 22));
        integration(new DimensionLabel(viewController, 320, 62));
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }

    private void integration(MenuSelectable...menus){
        Arrays.stream(menus).forEach(this::integration);
    }

    private void integration(AppComponent component) {
        component.integrationToPanel();
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
