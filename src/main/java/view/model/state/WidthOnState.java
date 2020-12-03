package view.model.state;

class WidthOnState implements WidthFieldState {

    private final CalculatorFieldState fieldState;

    WidthOnState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
        fieldState.actionState();
    }

    @Override
    public void actionState(){
        if(fieldState.isCheckBoxSelected()){
            fieldState.deactivate();
        }else {
            fieldState.activate();
        }
    }
}
