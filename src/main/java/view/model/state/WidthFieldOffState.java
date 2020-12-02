package view.model.state;

import view.view.AppComponent;

class WidthFieldOffState implements FieldState {

    private final CalculatorFieldState fieldState;

    WidthFieldOffState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void fieldAction(AppComponent width, AppComponent length) {
        fieldState.fieldDeactivate(width);
        fieldState.fieldActivate(length);
        // TODO 1
        //fieldState.addData(length);
    }
}
