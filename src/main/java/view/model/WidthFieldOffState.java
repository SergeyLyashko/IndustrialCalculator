package view.model;

import view.view.AppComponent;

class WidthFieldOffState implements FieldState {

    private final CalculatorFieldState fieldState;

    WidthFieldOffState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected() {
        //width.deactivate();
        //length.deactivate();
        System.out.print("test field OFF state menu select:");
        //System.out.println(" width OFF, length ON");
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        //System.out.print("test field OFF state checkbox select:");
        //System.out.println(" chBox not working");
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void fieldAction(AppComponent width, AppComponent length) {
        //System.out.println("test field OFF state field action");
        fieldState.fieldDeactivate(width);
        fieldState.fieldActivate(length);
    }
}
