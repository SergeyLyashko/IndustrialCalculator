package view.calculator;

import view.*;
import view.calculator.checkbox.ComplexAreaCheckBox;
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
    public void create(MenuReceiver menuReceiver, Visitor visitor){
        addInit(new ComplexAreaCheckBox(), visitor);
        addInit(new AssortmentsMenu(), visitor);
        addInit(new TypesMenu(), visitor);
        addInit(new NumbersMenu(), visitor);
        addInit(new Length(), visitor);
        addInit(new Width(), visitor);
        addInit(new Result(), visitor);
        addInit(new Message(), visitor);
        addInit(new DimensionLabel(320, 22), visitor);
        addInit(new DimensionLabel(320, 62), visitor);
    }

    private void addInit(AppComponent component, Visitor visitor) {
        Integrator initializer = component.getIntegrator();
        AppComponent initComponent = initializer.integration(component, visitor);
        components.add(initComponent);
    }

    @Override
    public List<AppComponent> getComponents() {
        return components;
    }
}
