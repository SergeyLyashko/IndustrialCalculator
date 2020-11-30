package view.model;

import view.view.AppComponent;

class WidthFieldOnState implements FieldState {

    private final CalculatorFieldState fieldState;

    WidthFieldOnState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected() {
        //width.deactivate();
        //length.deactivate();
        System.out.print("test field ON state menu select:");
        //System.out.println(" width ON, length ON");
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        //System.out.print("test field ON state checkbox select: "+checkBoxState);
        //System.out.println(" width OFF, length -> area");
        fieldState.setCheckBoxState(checkBoxState);
        fieldState.turnNumbers();
    }

    @Override
    public void fieldAction(AppComponent width, AppComponent length) {
        //System.out.println("test field ON state field action");
        if(fieldState.getCurrentCheckBoxState()){
            fieldState.fieldDeactivate(width);
            fieldState.areaActivate(length);
        }else {
            fieldState.fieldActivate(width);
            fieldState.fieldActivate(length);
        }
    }
}
