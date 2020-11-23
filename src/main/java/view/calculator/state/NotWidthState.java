package view.calculator.state;

import view.calculator.CalculatorFieldState;
import view.calculator.fields.FieldSelectable;

public class NotWidthState implements FieldState {

    private final CalculatorFieldState fieldState;

    public NotWidthState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width) {
        width.deactivate();
        fieldState.setState(fieldState.getWidthFieldOffState());
    }

    @Override
    public void turnNumbers() {

    }

    @Override
    public void fieldOn(FieldSelectable width) {

    }
}
