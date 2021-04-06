package viewcomponents.calculator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import view.AppComponent;
import view.CalculatorComponents;
import view.MenuSelectable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("calculatorComponents")
public class CalculatorComponentsImpl implements CalculatorComponents, InitializingBean, ApplicationContextAware {

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
    private AppComponent result;
    private AppComponent message;
    private ApplicationContext applicationContext;

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

    @Override
    public void afterPropertiesSet() throws Exception {
        integration(width);
        integration(length);
        integration(areaCheckBox);

        integration(assortment, types, numbers);
        addListeners(assortment, types, numbers);

        integration(result);
        integration(message);
        AppComponent dimensionWidth = applicationContext.getBean("dimension", AppComponent.class);
        dimensionWidth.setLocation(320, 22);
        AppComponent dimensionLength = applicationContext.getBean("dimension", AppComponent.class);
        dimensionLength.setLocation(320, 62);
        integration(dimensionWidth);
        integration(dimensionLength);
    }

    public CalculatorComponentsImpl(/*ViewController viewController, DataReceiver dataReceiver*/) {
        components = new ArrayList<>();

        //integration(new Width(viewController));
        //integration(new Length(viewController));
        //integration(new AreaCheckBox(viewController));

        //MenuSelectable assortment = new AssortmentsMenu(/*viewController, dataReceiver*/);
        //MenuSelectable types = new TypesMenu(/*viewController, dataReceiver*/);
        //MenuSelectable numbers = new NumbersMenu(/*viewController, dataReceiver*/);
        //integration(assortment, types, numbers);
        //addListeners(assortment, types, numbers);

        //integration(new Result(viewController));
        //integration(new Message(viewController));
        // TODO написать инициализацию !
        //integration(new DimensionLabel(viewController,320, 22));
        //integration(new DimensionLabel(viewController, 320, 62));
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
