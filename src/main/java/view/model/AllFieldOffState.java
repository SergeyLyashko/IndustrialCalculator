package view.model;

import view.controller.FieldSelectable;

public class AllFieldOffState implements FieldState {

    private final CalculatorFieldState fieldState;

    public AllFieldOffState(CalculatorFieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width, FieldSelectable length) {
        System.out.println("test All field off");
        width.deactivate();
        length.deactivate();
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void turnNumbers() {

    }

    @Override
    public void fieldAction(FieldSelectable width, FieldSelectable length) {

    }
}
