package view.model.state;

class HaveWidthFieldState implements FieldState {

    private final CalculatorFieldState fieldState;

    HaveWidthFieldState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected() {
        fieldState.setState(fieldState.getWidthFieldOnState());
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }
}
