package view.calculator.state;

import view.calculator.CalculatorFieldState;
import view.calculator.FieldState;
import view.calculator.fields.FieldSelectable;

public class WidthFieldOnState implements FieldState {

    private final CalculatorFieldState fieldState;

    public WidthFieldOnState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width, FieldSelectable length) {
        width.deactivate();
        length.deactivate();
        //System.out.print("test field ON state menu select:");
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
    public void turnNumbers() {
        //System.out.println("test field ON state turn num");
    }

    @Override
    public void fieldAction(FieldSelectable width, FieldSelectable length) {
        //System.out.println("test field ON state field action");
        if(fieldState.getCurrentCheckBoxState()){
            width.transformArea();
            length.transformArea();
        }else {
            width.activate();
            length.activate();
        }
    }
}
