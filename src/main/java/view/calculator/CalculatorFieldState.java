package view.calculator;

import view.calculator.fields.FieldSelectable;
import view.calculator.state.*;

public class CalculatorFieldState {

    private final FieldSelectable width;
    private final FieldSelectable length;
    private FieldState state;

    private final FieldState widthFieldOffState;
    private final FieldState widthFieldOnState;
    private final FieldState haveWidthState;
    private final FieldState notWidthState;


    public CalculatorFieldState(FieldSelectable width, FieldSelectable length){
        this.width = width;
        this.length = length;
        haveWidthState = new HaveWidthState(this);
        notWidthState = new NotWidthState(this);
        widthFieldOffState = new WidthFieldOffState(this);
        widthFieldOnState = new WidthFieldOnState(this);
        state = notWidthState;
    }


    public void selectMenu(){
        state.menuSelected(width);
    }

    public void turnNumbers(){
        state.turnNumbers();
        state.fieldOn(width);
    }

    public void setState(FieldState state){
        this.state = state;
    }

    public FieldState getCurrentState(){
        return state;
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
}
