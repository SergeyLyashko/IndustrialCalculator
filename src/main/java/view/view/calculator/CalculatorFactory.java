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

    private final MenuSelectable assortment;
    private final MenuSelectable types;
    private final MenuSelectable numbers;

    private final FieldSelectable width;
    private final FieldSelectable length;

    private final AppComponent complexAreaCheckBox;
    private final Result result;
    private final Message message;
    private final DimensionLabel dimensionWidth;
    private final DimensionLabel dimensionLength;

    public CalculatorFactory(ViewController viewController) {
        this.viewController = viewController;
        components = new ArrayList<>();

        assortment = new AssortmentsMenu(viewController);
        types = new TypesMenu(viewController);
        numbers = new NumbersMenu(viewController);

        width = new Width(viewController);
        length = new Length(viewController);

        complexAreaCheckBox = new AreaSettableCheckBox(viewController);

        result = new Result(viewController);
        message = new Message(viewController);
        dimensionWidth = new DimensionLabel(viewController,320, 22);
        dimensionLength = new DimensionLabel(viewController, 320, 62);
    }

    @Override
    public List<AppComponent> createComponents(){

        Visitor controllerVisitor = viewController.getVisitor();
        createDefaultMenu(assortment, types, numbers);

        integration(complexAreaCheckBox, controllerVisitor);
        integration(width, controllerVisitor);
        integration(length, controllerVisitor);

        integration(result, controllerVisitor);
        integration(message, controllerVisitor);
        integration(dimensionWidth, controllerVisitor);
        integration(dimensionLength, controllerVisitor);

        return components;
    }

    private void integration(AppComponent component, Visitor visitor) {
        component.integrationToPanel();
        components.add(component);
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
