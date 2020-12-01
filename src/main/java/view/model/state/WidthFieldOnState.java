package view.model.state;

import view.view.AppComponent;

class WidthFieldOnState implements FieldState {

    private final CalculatorFieldState fieldState;

    WidthFieldOnState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        fieldState.setCheckBoxState(checkBoxState);
        fieldState.turnNumbers();
    }

    @Override
    public void fieldAction(AppComponent width, AppComponent length) {
        if(fieldState.getCurrentCheckBoxState()){
            fieldState.fieldDeactivate(width);
            fieldState.areaActivate(length);
            // TODO 1 area
            fieldState.createAreaData(length);
        }else {
            fieldState.fieldActivate(width);
            fieldState.fieldActivate(length);
            // TODO 2
            fieldState.createData(width, length);
        }
    }
}
