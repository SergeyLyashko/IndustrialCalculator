package view.calculator;

import view.*;
import view.calculator.checkbox.ComplexAreaCheckBox;
import view.calculator.fields.Length;
import view.calculator.fields.Width;
import view.calculator.menuboxes.AssortmentsMenu;
import view.calculator.menuboxes.MenuModel;
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

        MenuWrapper menuWrapper = new MenuWrapper(receivableMenu);
        menuWrapper.createComponents(new AssortmentsMenu(), new TypesMenu(), new NumbersMenu());
        menuWrapper.getComponents().forEach((AppComponent element) -> integration(element, visitor));

        integration(new ComplexAreaCheckBox(), visitor);
        integration(new Length(), visitor);
        integration(new Width(), visitor);
        integration(new Result(), visitor);
        integration(new Message(), visitor);
        integration(new DimensionLabel(320, 22), visitor);
        integration(new DimensionLabel(320, 62), visitor);
    }

    private void integration(AppComponent component, Visitor visitor) {
        component.integration(visitor);
        components.add(component);
    }

    @Override
    public List<AppComponent> getComponents() {
        return components;
    }
}
