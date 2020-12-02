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
        fieldState.actionState();
    }

    @Override
    public void fieldAction(AppComponent width, AppComponent length) {
        if(fieldState.isCheckBoxAction()){
            fieldState.fieldDeactivate(width);
            fieldState.areaActivate(length);
            // TODO 1 area
            //fieldState.addAreaData(length);
        }else {
            fieldState.fieldActivate(width);
            fieldState.fieldActivate(length);
            // TODO 2
            //fieldState.addData(width);
            //fieldState.addData(length);
        }
    }
}
