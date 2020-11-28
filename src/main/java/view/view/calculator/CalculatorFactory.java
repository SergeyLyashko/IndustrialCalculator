package view.view.calculator;

import view.controller.*;
import view.view.AppComponent;
import view.view.ComponentsFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorFactory implements ComponentsFactory {

    private final List<AppComponent> components;
    private final ViewController viewController;

    public CalculatorFactory(ViewController viewController) {
        this.viewController = viewController;
        components = new ArrayList<>();

        MenuSelectable assortment = new AssortmentsMenu(viewController);
        MenuSelectable types = new TypesMenu(viewController);
        MenuSelectable numbers = new NumbersMenu(viewController);
        createDefaultMenu(assortment, types, numbers);

        integration(new Width(viewController));
        integration(new Length(viewController));
        integration(new AreaSettableCheckBox(viewController));

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
            viewController.selectMenu(element, "");
            integration(element);
        });
    }

    private void addListeners(MenuSelectable...menus) {
        MenuSelectable assortment = menus[0];
        MenuSelectable types = menus[1];
        MenuSelectable numbers = menus[2];
        assortment.addChildMenu(types);
        assortment.addChildMenu(numbers);
        types.addChildMenu(numbers);
    }
}
