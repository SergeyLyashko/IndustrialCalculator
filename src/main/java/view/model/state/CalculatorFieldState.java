package view.model.state;

import view.model.*;
import view.view.AppComponent;

public class CalculatorFieldState {

    private final ViewModelImpl viewModel;
    private AppComponent width;
    private AppComponent length;
    private FieldState state;

    private final FieldState allFieldOffState;
    private final FieldState widthFieldOffState;
    private final FieldState widthFieldOnState;
    private final FieldState haveWidthState;
    private final FieldState notWidthState;
    private boolean checkBoxState;

    public CalculatorFieldState(ViewModelImpl viewModel){
        this.viewModel = viewModel;

        allFieldOffState = new AllFieldOffState(this);
        haveWidthState = new HaveWidthFieldState(this);
        notWidthState = new NotWidthFieldState(this);
        widthFieldOffState = new WidthFieldOffState(this);
        widthFieldOnState = new WidthFieldOnState(this);
        state = allFieldOffState;
    }

    // TODO ??
    public void setField(AppComponent component){
        // TODO new
        state.action(component);

        String name = component.getName();
        if(name.equalsIgnoreCase("введите ширину")){
            width = component;
        }else{
            length = component;
        }
    }

    // TODO
    public void actionState(){
        state.fieldAction(width, length);
        //
        state.action(width);
        state.action(length);
    }

    public void selectMenu(){
        state.menuSelected();
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

    public FieldState getHaveWidthState(){
        return haveWidthState;
    }

    public FieldState getNotWidthState(){
        return notWidthState;
    }

    public FieldState getWidthFieldOnState(){
        return widthFieldOnState;
    }

    public FieldState getWidthFieldOffState(){
        return widthFieldOffState;
    }

    public void fieldActivate(AppComponent component) {
        viewModel.fieldActivate(component);
        viewModel.addData(component);
    }

    public void fieldDeactivate(AppComponent component) {
        viewModel.fieldDeactivate(component);
    }

    public void areaActivate(AppComponent component){
        viewModel.areaActivate(component);
        viewModel.addAreaData(component);
    }
}
