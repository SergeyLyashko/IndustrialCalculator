package view.view;

import view.controller.*;
import view.model.CalculatorFieldState;
import view.model.MenuWrapper;
import view.view.checkboxes.ComplexAreaCheckBox;
import view.view.fields.Length;
import view.view.fields.Width;
import view.view.menuboxes.AssortmentsMenu;
import view.view.menuboxes.NumbersMenu;
import view.view.menuboxes.TypesMenu;
import view.view.textlabels.DimensionLabel;
import view.view.textlabels.Message;
import view.view.textlabels.Result;

import java.util.ArrayList;
import java.util.List;

public class CalculatorFactory implements ComponentsFactory {

    private final List<AppComponent> components = new ArrayList<>();

    @Override
    public List<AppComponent> createComponents(MenuReceivable menuReceivable, Visitor visitor){

        MenuSelectable assortment = new AssortmentsMenu();
        MenuSelectable types = new TypesMenu();
        MenuSelectable numbers = new NumbersMenu();

        MenuWrapper menuWrapper = new MenuWrapper(menuReceivable);
        menuWrapper.createMenu(assortment, types, numbers);
        menuWrapper.getComponents().forEach(this::integration);

        FieldSelectable width = new Width();
        FieldSelectable length = new Length();

        AppComponent complexAreaCheckBox = new ComplexAreaCheckBox();

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
