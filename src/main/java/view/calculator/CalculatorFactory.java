package view.calculator;

import view.*;
import view.calculator.checkbox.ComplexAreaCheckBox;
import view.calculator.fields.FieldSelectable;
import view.calculator.fields.Length;
import view.calculator.fields.Width;
import view.calculator.menuboxes.AssortmentsMenu;
import view.calculator.menuboxes.NumbersMenu;
import view.calculator.menuboxes.TypesMenu;
import view.calculator.textlabels.DimensionLabel;
import view.calculator.textlabels.Message;
import view.calculator.textlabels.Result;

import java.util.ArrayList;
import java.util.List;

public class CalculatorFactory implements ComponentsFactory {

    private final List<AppComponent> components = new ArrayList<>();

    @Override
    public void create(ReceivableMenu receivableMenu, Visitor visitor){

        SelectableMenu assortment = new AssortmentsMenu();
        SelectableMenu types = new TypesMenu();
        SelectableMenu numbers = new NumbersMenu();

        MenuWrapper menuWrapper = new MenuWrapper(receivableMenu);
        menuWrapper.createMenu(assortment, types, numbers);
        menuWrapper.getComponents().forEach(this::integration);

        FieldSelectable width = new Width();
        FieldSelectable length = new Length();

        assortment.addListener(width);
        assortment.addListener(length);

        types.addListener(length);
        types.addListener(width);

        numbers.addListener(length);
        numbers.addListener(width);


        integration(new ComplexAreaCheckBox(), visitor);
        integration(length, visitor);
        integration(width, visitor);
        integration(new Result(), visitor);
        integration(new Message(), visitor);
        integration(new DimensionLabel(320, 22), visitor);
        integration(new DimensionLabel(320, 62), visitor);
    }

    private void integration(AppComponent component, Visitor visitor) {
        component.integration();
        component.registerHost(visitor);
        components.add(component);
    }

    private void integration(AppComponent component) {
        component.integration();
        components.add(component);
    }

    @Override
    public List<AppComponent> getComponentList() {
        return components;
    }
}
