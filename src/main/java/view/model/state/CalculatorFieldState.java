package view.model.state;

import view.model.*;

public class CalculatorFieldState {

    private final ViewModelImpl viewModel;
    private WidthFieldState state;

    private final WidthFieldState allFieldOffState;
    private final WidthFieldState widthFieldOnState;
    private boolean checkBoxState;

    public CalculatorFieldState(ViewModelImpl viewModel){
        this.viewModel = viewModel;
        allFieldOffState = new AllFieldOffState(this);
        widthFieldOnState = new WidthOnState(this);
        state = allFieldOffState;
    }

    public void actionState(){
        state.actionState();
    }

    public void checkBoxSelect(boolean checkBoxState){
        state.checkBoxState(checkBoxState);
    }

    public void setState(WidthFieldState state){
        this.state = state;
    }

    public void setCheckBoxState(boolean checkBoxState){
        this.checkBoxState = checkBoxState;
    }

    public boolean isCheckBoxSelected(){
        return checkBoxState;
    }

    public WidthFieldState getAllFieldOffState(){
        return allFieldOffState;
    }

    public WidthFieldState getWidthFieldOnState(){
        return widthFieldOnState;
    }

    void activate() {
        viewModel.widthActivate();
    }

    void deactivate() {
        viewModel.widthDeactivate();
    }
}
