package view.model.state;

import view.model.*;
import view.view.AppComponent;

public class CalculatorFieldState {

    private final ViewModelImpl viewModel;
    private FieldState state;

    private final FieldState allFieldOffState;
    private final FieldState widthFieldOnState;
    private boolean checkBoxState;
    private AppComponent component;

    public CalculatorFieldState(ViewModelImpl viewModel){
        this.viewModel = viewModel;

        allFieldOffState = new AllFieldOffState(this);
        widthFieldOnState = new WidthOnState(this);
        state = allFieldOffState;
    }

    public void setField(AppComponent component){
        this.component = component;
    }

    public void actionState(){
        state.action(component);
    }

    public void checkBoxSelect(boolean checkBoxState){
        state.checkBoxState(checkBoxState);
    }

    public void setState(FieldState state){
        this.state = state;
    }

    public void setCheckBoxState(boolean checkBoxState){
        this.checkBoxState = checkBoxState;
    }

    public boolean isCheckBoxAction(){
        return checkBoxState;
    }

    public FieldState getAllFieldOffState(){
        return allFieldOffState;
    }

    public FieldState getWidthFieldOnState(){
        return widthFieldOnState;
    }

    public void fieldActivate(AppComponent component) {
        viewModel.fieldActivate(component);
    }

    public void fieldDeactivate(AppComponent component) {
        viewModel.fieldDeactivate(component);
    }

    public void areaActivate(){
        viewModel.areaActivate();
    }

    public void areaDeactivate() {
        viewModel.areaDeactivate();
    }
}
