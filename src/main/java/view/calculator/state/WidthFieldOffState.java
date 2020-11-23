package view.calculator.state;

import view.calculator.CalculatorFieldState;
import view.calculator.fields.FieldSelectable;

public class WidthFieldOffState implements FieldState {

    private final CalculatorFieldState fieldState;

    public WidthFieldOffState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width) {
        width.deactivate();
        System.out.println("test off state type menu select");//nothing
    }

    @Override
    public void turnNumbers() {
        System.out.println("width field off state turn numbers");
    }

    @Override
    public void fieldOn(FieldSelectable width) {
        width.deactivate();
        System.out.println("width field off");
        fieldState.setState(fieldState.getWidthFieldOffState());
    }
}
