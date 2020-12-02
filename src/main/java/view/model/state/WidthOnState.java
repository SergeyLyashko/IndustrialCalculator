package view.model.state;

import view.view.AppComponent;

class WidthOnState implements FieldState {

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
    public void action(AppComponent component){
        if(fieldState.isCheckBoxAction()){
            fieldState.fieldDeactivate(component);
            fieldState.areaActivate();
        }else {
            fieldState.fieldActivate(component);
            fieldState.areaDeactivate();
        }
    }
}
