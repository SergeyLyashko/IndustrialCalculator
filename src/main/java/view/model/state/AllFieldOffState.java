package view.model.state;

import view.view.AppComponent;

public class AllFieldOffState implements FieldState {

    private final CalculatorFieldState fieldState;

    public AllFieldOffState(CalculatorFieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void fieldAction(AppComponent width, AppComponent length) {
        fieldState.fieldDeactivate(width);
        fieldState.fieldDeactivate(length);
    }
}
