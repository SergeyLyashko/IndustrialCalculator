package view.model;

import view.controller.FieldSelectable;

public class AllFieldOffState implements FieldState {

    private final CalculatorFieldState fieldState;

    public AllFieldOffState(CalculatorFieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected() {

    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void fieldAction(FieldSelectable width, FieldSelectable length) {
        width.deactivate();
        length.deactivate();
    }
}
