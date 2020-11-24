package view.calculator.state;

import view.calculator.CalculatorFieldState;
import view.calculator.fields.FieldSelectable;

public class HaveWidthState implements FieldState {

    private final CalculatorFieldState fieldState;

    public HaveWidthState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width, FieldSelectable length) {
        width.deactivate();
        length.deactivate();
        System.out.print("test have width menu select:");
        System.out.println(" width ON, length ON");
        fieldState.setState(fieldState.getWidthFieldOnState());
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        System.out.print("test have width checkbox select:");
        System.out.println(" width OFF, length -> area");
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void turnNumbers() {
        System.out.println("test have width state turn num");
    }

    @Override
    public void fieldAction(FieldSelectable width, FieldSelectable length) {
        System.out.println("test have width state field action");
    }
}
