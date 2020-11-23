package view.calculator.state;

import view.calculator.CalculatorFieldState;
import view.calculator.fields.FieldSelectable;

public class HaveWidthState implements FieldState {

    private final CalculatorFieldState fieldState;

    public HaveWidthState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width) {
        width.deactivate();
        fieldState.setState(fieldState.getWidthFieldOnState());
    }

    @Override
    public void turnNumbers() {

    }

    @Override
    public void fieldOn(FieldSelectable width) {

    }
}
