package view.model.state;

public class AllFieldOffState implements WidthFieldState {

    private final CalculatorFieldState fieldState;

    public AllFieldOffState(CalculatorFieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }
}
