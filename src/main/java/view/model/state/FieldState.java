package view.model.state;

import view.model.FieldBehavior;
import view.model.State;

public class FieldState implements State {

    private WidthFieldState state;

    private final WidthFieldState allFieldOffState;
    private final WidthFieldState widthFieldOnState;
    private boolean checkBoxState;

    private FieldBehavior lengthBehavior;
    private FieldBehavior widthBehavior;

    private boolean areaStatus;
    private boolean widthStatus;

    public FieldState(){
        allFieldOffState = new AllFieldOffState(this);
        widthFieldOnState = new WidthOnState(this);
        state = allFieldOffState;
    }

    @Override
    public void setLengthBehavior(FieldBehavior lengthBehavior) {
        this.lengthBehavior = lengthBehavior;
        lengthBehavior.fieldDeactivate();
    }

    @Override
    public void setWidthBehavior(FieldBehavior widthBehavior) {
        this.widthBehavior = widthBehavior;
        widthBehavior.fieldDeactivate();
    }

    @Override
    public void actionState(){
        lengthBehavior.fieldActivate();
        state.actionState();
    }

    @Override
    public void checkBoxSelect(boolean checkBoxState){
        state.checkBoxState(checkBoxState);
    }

    void setCheckBoxState(boolean checkBoxState){
        this.checkBoxState = checkBoxState;
    }

    boolean isCheckBoxSelected(){
        return checkBoxState;
    }

    @Override
    public void setFieldsOff() {
        this.state = allFieldOffState;
        resetField();
    }

    @Override
    public void setWidthOn() {
        this.state = widthFieldOnState;
        widthStatus = true;
    }

    void activate() {
        widthBehavior.fieldActivate();
        areaDeactivate();
        widthStatus = true;
    }

    void deactivate() {
        widthBehavior.fieldDeactivate();
        areaActivate();
        widthStatus = false;
    }

    private void areaActivate(){
        lengthBehavior.areaActivate();
        areaStatus = true;
    }

    private void areaDeactivate(){
        lengthBehavior.areaDeactivate();
        areaStatus = false;
    }

    private void resetField(){
        widthBehavior.fieldDeactivate();
        lengthBehavior.fieldDeactivate();
        widthStatus = false;
    }

    public boolean isArea(){
        return areaStatus;
    }

    public boolean isWidth(){
        return widthStatus;
    }
}
