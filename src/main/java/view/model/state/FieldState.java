package view.model.state;

import view.model.*;

public class FieldState implements State {

    private final ViewModel viewModel;
    private WidthFieldState state;

    private final WidthFieldState allFieldOffState;
    private final WidthFieldState widthFieldOnState;
    private boolean checkBoxState;

    public FieldState(ViewModel viewModel){
        this.viewModel = viewModel;
        allFieldOffState = new AllFieldOffState(this);
        widthFieldOnState = new WidthOnState(this);
        state = allFieldOffState;
    }

    @Override
    public void actionState(){
        state.actionState();
    }

    @Override
    public void checkBoxSelect(boolean checkBoxState){
        state.checkBoxState(checkBoxState);
    }

    public void setCheckBoxState(boolean checkBoxState){
        this.checkBoxState = checkBoxState;
    }

    public boolean isCheckBoxSelected(){
        return checkBoxState;
    }

    @Override
    public void setFieldsOff() {
        this.state = allFieldOffState;
    }

    @Override
    public void setWidthOn() {
        this.state = widthFieldOnState;
    }
///////////////////////////////////////////////////////
    void activate() {
        viewModel.widthActivate();
    }

    void deactivate() {
        viewModel.widthDeactivate();
    }
}
