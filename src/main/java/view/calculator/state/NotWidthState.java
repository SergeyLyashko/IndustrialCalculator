package view.calculator.state;

import view.calculator.CalculatorFieldState;
import view.calculator.FieldState;
import view.calculator.fields.FieldSelectable;

public class NotWidthState implements FieldState {

    private final CalculatorFieldState fieldState;

    public NotWidthState(CalculatorFieldState fieldState){
        this.fieldState = fieldState;
    }

    @Override
    public void menuSelected(FieldSelectable width, FieldSelectable length) {
        width.deactivate();
        length.deactivate();
        //System.out.print("test NOT width state menu select");
        //System.out.println(" width OFF, length ON");
        fieldState.setState(fieldState.getWidthFieldOffState());
    }

    @Override
    public void checkBoxState(boolean checkBoxState) {
        //System.out.println("test NOT width state checkbox select");
        //System.out.println(" chBox not working");
        fieldState.setCheckBoxState(checkBoxState);
    }

    @Override
    public void turnNumbers() {
        //System.out.println("test NOT width state turn num");
    }

    @Override
    public void fieldAction(FieldSelectable width, FieldSelectable length) {
        //System.out.println("test NOT width state field action");
    }
}