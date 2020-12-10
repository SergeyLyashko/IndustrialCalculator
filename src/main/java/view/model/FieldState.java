package view.model;

public class FieldState implements State {

    private FieldBehavior lengthBehavior;
    private FieldBehavior widthBehavior;

    private boolean checkBoxSelected;
    private boolean areaStatus;
    private boolean widthStatus;

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

    @Override
    public void checkBoxSelect(boolean checkBoxState){
        this.checkBoxSelected = checkBoxState;
        action();
    }

    @Override
    public void fieldsOff() {
        widthBehavior.fieldDeactivate();
        lengthBehavior.fieldDeactivate();
        widthStatus = false;
    }

    @Override
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
