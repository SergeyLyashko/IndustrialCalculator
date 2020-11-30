package view.model;

import view.view.AppComponent;

class HaveWidthFieldState implements FieldState {

    private final CalculatorFieldState fieldState;

    HaveWidthFieldState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected() {
        //System.out.print("test have width menu select:");
        //System.out.println(" width ON, length ON");
        fieldState.setState(fieldState.getWidthFieldOnState());
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        //System.out.print("test have width checkbox select:");
        //System.out.println(" width OFF, length -> area");
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void fieldAction(AppComponent width, AppComponent length) {
        //System.out.println("test have width state field action");
    }
}
