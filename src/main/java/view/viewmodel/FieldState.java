package view.viewmodel;

class FieldState {

    private Behavior lengthBehavior;
    private Behavior widthBehavior;

    private boolean checkBoxSelected;
    private boolean areaStatus;
    private boolean widthStatus;

    void setLengthBehavior(Behavior lengthBehavior) {
        this.lengthBehavior = lengthBehavior;
        lengthBehavior.fieldDeactivate();
    }

    void setWidthBehavior(Behavior widthBehavior) {
        this.widthBehavior = widthBehavior;
        widthBehavior.fieldDeactivate();
    }

    void action(){
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

    void checkBoxSelect(boolean checkBoxState){
        this.checkBoxSelected = checkBoxState;
        action();
    }

    void fieldsOff() {
        widthBehavior.fieldDeactivate();
        lengthBehavior.fieldDeactivate();
        widthStatus = false;
    }

    void widthOn() {
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

    boolean isArea(){
        return areaStatus;
    }

    boolean isWidth(){
        return widthStatus;
    }
}
