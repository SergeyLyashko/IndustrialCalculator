package view.viewcalculator;

import view.DataBaseMenuReceiver;
import view.viewcontroller.*;
import view.AppComponent;
import view.ComponentsFactory;
import view.MenuSelectable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorComponentsFactory implements ComponentsFactory {

    private final List<AppComponent> components;
    private final ViewController viewController;

    public CalculatorComponentsFactory(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver) {
        this.viewController = viewController;
        components = new ArrayList<>();

        integration(new Width(viewController));
        integration(new Length(viewController));
        integration(new AreaSettableCheckBox(viewController));

        MenuSelectable assortment = new AssortmentsMenu(viewController, dataBaseMenuReceiver);
        MenuSelectable types = new TypesMenu(viewController, dataBaseMenuReceiver);
        MenuSelectable numbers = new NumbersMenu(viewController, dataBaseMenuReceiver);
        createDefaultMenu(assortment, types, numbers);

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

    private void createDefaultMenu(MenuSelectable...menus){
        addListeners(menus);
        Arrays.stream(menus).forEach(element -> {
            element.receiveMenu();
            integration(element);
        });
    }

    private void addListeners(MenuSelectable...menus) {
        MenuSelectable assortment = menus[0];
        MenuSelectable types = menus[1];
        MenuSelectable numbers = menus[2];
        assortment.addListenerMenu(types);
        assortment.addListenerMenu(numbers);
        types.addListenerMenu(numbers);
    }
}