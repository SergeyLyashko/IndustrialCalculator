package view.calculator.state;

import view.calculator.CalculatorFieldState;
import view.calculator.fields.FieldSelectable;

public class WidthFieldOnState implements FieldState {

    private final CalculatorFieldState fieldState;

    public WidthFieldOnState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width) {
        width.deactivate();
        System.out.println("test off state menu select");//nothing
    }

    @Override
    public void turnNumbers() {
        System.out.println("width field on state turn numbers");
    }

    @Override
    public void fieldOn(FieldSelectable width) {
        width.activate();
        System.out.println("поле включено");
        fieldState.setState(fieldState.getWidthFieldOffState());
    }
}
