package view.model.state;

import view.model.*;
import view.model.behavior.FieldBehavior;
import view.view.AppComponent;

public class FieldState implements State {

    private final KeyActionObserver observer;
    private WidthFieldState state;

    private final WidthFieldState allFieldOffState;
    private final WidthFieldState widthFieldOnState;
    private boolean checkBoxState;

    private Behavior lengthBehavior;
    private Behavior widthBehavior;

    private boolean areaStatus;
    private boolean widthStatus;

    public FieldState(KeyActionObserver observer){
        this.observer = observer;
        allFieldOffState = new AllFieldOffState(this);
        widthFieldOnState = new WidthOnState(this);
        state = allFieldOffState;
    }

    @Override
    public void setLength(AppComponent length) {
        lengthBehavior = new FieldBehavior(length);
        lengthBehavior.registerObserver(observer);
    }

    @Override
    public void setWidth(AppComponent width) {
        widthBehavior = new FieldBehavior(width);
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
