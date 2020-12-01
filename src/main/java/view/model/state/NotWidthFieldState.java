package view.model.state;

class NotWidthFieldState implements FieldState {

    private final CalculatorFieldState fieldState;

    NotWidthFieldState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected() {
        fieldState.setState(fieldState.getWidthFieldOffState());
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }
}
