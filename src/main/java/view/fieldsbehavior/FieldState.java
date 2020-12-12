package view.fieldsbehavior;

import view.AppComponent;
import view.viewmodel.KeyActionObserver;

public class FieldState {

    private final KeyActionObserver observer;
    private Behavior lengthBehavior;
    private Behavior widthBehavior;

    private boolean checkBoxSelected;
    private boolean areaStatus;
    private boolean widthStatus;

    public FieldState(KeyActionObserver observer) {
        this.observer = observer;
    }

    public void setLength(AppComponent length) {
        lengthBehavior = new FieldBehaviorImpl(length);
        lengthBehavior.fieldDeactivate();
        lengthBehavior.registerObserver(observer);
    }

    public void setWidth(AppComponent width) {
        this.widthBehavior = new FieldBehaviorImpl(width);
        widthBehavior.fieldDeactivate();
    }

    public void action(){
        lengthBehavior.fieldActivate();
        if(widthStatus){
            checkSelected();
        }
    }

    private void checkSelected(){
        if(checkBoxSelected){
            deactivate();
        }else {
            activate();
        }
    }

    public void checkBoxSelect(boolean checkBoxState){
        this.checkBoxSelected = checkBoxState;
        action();
    }

    public void fieldsOff() {
        widthBehavior.fieldDeactivate();
        lengthBehavior.fieldDeactivate();
        widthStatus = false;
    }

    public void widthOn() {
        widthStatus = true;
    }

    private void activate() {
        widthBehavior.fieldActivate();
        areaDeactivate();
    }

    private void deactivate() {
        widthBehavior.fieldDeactivate();
        areaActivate();
    }

    private void areaActivate(){
        lengthBehavior.areaActivate();
        areaStatus = true;
    }

    private void areaDeactivate(){
        lengthBehavior.areaDeactivate();
        areaStatus = false;
    }

    public boolean isArea(){
        return areaStatus;
    }

    public boolean isWidth(){
        return widthStatus;
    }
}
