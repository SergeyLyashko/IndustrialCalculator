package view.model;

import view.controller.FieldSelectable;

class CalculatorFieldState {

    private final ViewModelImpl viewModel;
    private FieldSelectable width;
    private FieldSelectable length;
    private FieldState state;

    private final FieldState allFieldOffState;
    private final FieldState widthFieldOffState;
    private final FieldState widthFieldOnState;
    private final FieldState haveWidthState;
    private final FieldState notWidthState;
    private boolean checkBoxState;

    CalculatorFieldState(ViewModelImpl viewModel){
        this.viewModel = viewModel;

        allFieldOffState = new AllFieldOffState(this);
        haveWidthState = new HaveWidthFieldState(this);
        notWidthState = new NotWidthFieldState(this);
        widthFieldOffState = new WidthFieldOffState(this);
        widthFieldOnState = new WidthFieldOnState(this);

        state = notWidthState;
    }

    void setField(FieldSelectable fieldSelectable){
        String name = fieldSelectable.getName();
        if(name.equalsIgnoreCase("введите ширину")){
            width = fieldSelectable;
        }else{
            length = fieldSelectable;
        }
    }

    void checkBoxSelect(boolean checkBoxState){
        state.checkBoxState(checkBoxState);
    }

    void selectMenu(){
        state.menuSelected(width, length);
    }

    void turnNumbers(){
        state.turnNumbers();
        state.fieldAction(width, length);
    }

    void setState(FieldState state){
        this.state = state;
    }

    void setCheckBoxState(boolean checkBoxState){
        this.checkBoxState = checkBoxState;
    }

    boolean getCurrentCheckBoxState(){
        return checkBoxState;
    }

    FieldState getAllFieldOffState(){
        return allFieldOffState;
    }

    FieldState getHaveWidthState(){
        return haveWidthState;
    }

    FieldState getNotWidthState(){
        return notWidthState;
    }

    FieldState getWidthFieldOnState(){
        return widthFieldOnState;
    }

    FieldState getWidthFieldOffState(){
        return widthFieldOffState;
    }


    public void createNotWidthData(FieldSelectable length) {
        NotWidthData notWidthData = new NotWidthData(length);
    }
}
