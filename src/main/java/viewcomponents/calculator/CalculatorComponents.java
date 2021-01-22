package viewcomponents.calculator;

import view.DataReceiver;
import view.AppComponent;
import view.ComponentsList;
import view.MenuSelectable;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorComponents implements ComponentsList {

    private final List<AppComponent> components;

    public CalculatorComponents(ViewController viewController, DataReceiver dataReceiver) {
        components = new ArrayList<>();

        integration(new Width(viewController));
        integration(new Length(viewController));
        integration(new SetAreaCheckBox(viewController));

        MenuSelectable assortment = new AssortmentsMenu(viewController, dataReceiver);
        MenuSelectable types = new TypesMenu(viewController, dataReceiver);
        MenuSelectable numbers = new NumbersMenu(viewController, dataReceiver);
        integration(assortment, types, numbers);
        addListeners(assortment, types, numbers);

        integration(new Result(viewController));
        integration(new Message(viewController));
        integration(new DimensionLabel(viewController,320, 22));
        integration(new DimensionLabel(viewController, 320, 62));
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }

    private void integration(AppComponent component) {
        component.integrationToPanel();
        components.add(component);
    }

    private void integration(MenuSelectable...menus){
        Arrays.stream(menus).forEach(this::integration);
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
