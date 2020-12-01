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

    public void setField(AppComponent fieldSelectable){
        String name = fieldSelectable.getName();
        if(name.equalsIgnoreCase("введите ширину")){
            width = fieldSelectable;
        }else{
            length = fieldSelectable;
        }
    }

    public void checkBoxSelect(boolean checkBoxState){
        state.checkBoxState(checkBoxState);
    }

    public void selectMenu(){
        state.menuSelected();
    }

    public void turnNumbers(){
        state.fieldAction(width, length);
    }

    public void setState(FieldState state){
        this.state = state;
    }

    public void setCheckBoxState(boolean checkBoxState){
        this.checkBoxState = checkBoxState;
    }

    public boolean getCurrentCheckBoxState(){
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

    public void fieldActivate(AppComponent fieldSelectable) {
        viewModel.fieldActivate(fieldSelectable);
    }

    public void fieldDeactivate(AppComponent fieldSelectable) {
        viewModel.fieldDeactivate(fieldSelectable);
    }

    public void areaActivate(AppComponent fieldSelectable){
        viewModel.areaActivate(fieldSelectable);
    }

    public void createData(AppComponent...components) {
        viewModel.createData(components);
    }

    public void createAreaData(AppComponent...components) {
        viewModel.createAreaData(components);
    }
}
