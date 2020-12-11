package view.calculatorcomponents;

import view.DataBaseMenuReceiver;
import view.AppComponent;
import view.ComponentsList;
import view.MenuSelectable;
import view.ViewController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorComponentsDI implements ComponentsList {

    private final List<AppComponent> components;

    public CalculatorComponentsDI(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver) {
        components = new ArrayList<>();

        integration(new Width(viewController));
        integration(new Length(viewController));
        integration(new AreaSettableCheckBox(viewController));

        MenuSelectable assortment = new AssortmentsMenu(viewController, dataBaseMenuReceiver);
        MenuSelectable types = new TypesMenu(viewController, dataBaseMenuReceiver);
        MenuSelectable numbers = new NumbersMenu(viewController, dataBaseMenuReceiver);
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
