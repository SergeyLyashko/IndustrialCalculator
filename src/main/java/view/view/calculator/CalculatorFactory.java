package view.view.calculator;

import view.controller.*;
import view.model.CalculatorFieldState;
import view.view.AppComponent;
import view.view.ComponentsFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculatorFactory implements ComponentsFactory {

    private final List<AppComponent> components = new ArrayList<>();

    @Override
    public List<AppComponent> createComponents(ViewController viewController, Visitor visitor){

        MenuSelectable assortment = new AssortmentsMenu(viewController);
        MenuSelectable types = new TypesMenu(viewController);
        MenuSelectable numbers = new NumbersMenu();

        DefaultMenuBoxes defaultMenuBoxes = new DefaultMenuBoxes(viewController);
        defaultMenuBoxes.createDefaultMenu(assortment, types, numbers);
        defaultMenuBoxes.getComponents().forEach(this::integration);

        FieldSelectable width = new Width(viewController);
        FieldSelectable length = new Length(viewController);

        AppComponent complexAreaCheckBox = new ComplexAreaCheckBox(viewController);

        CalculatorFieldState calculatorFieldState = new CalculatorFieldState(width, length);
        assortment.addFieldStateListener(calculatorFieldState);
        types.addFieldStateListener(calculatorFieldState);
        numbers.addFieldStateListener(calculatorFieldState);
        complexAreaCheckBox.addFieldStateListener(calculatorFieldState);

        integration(complexAreaCheckBox, visitor);
        integration(width, visitor);
        integration(length, visitor);
        integration(new Result(), visitor);
        integration(new Message(), visitor);
        integration(new DimensionLabel(320, 22), visitor);
        integration(new DimensionLabel(320, 62), visitor);

        return components;
    }

    private void integration(AppComponent component, Visitor visitor) {
        component.integration();
        component.registerAsHost(visitor);
        components.add(component);
    }

    private void integration(AppComponent component) {
        component.integration();
        components.add(component);
    }
}
