package view.model;

import view.controller.*;
import view.model.behavior.FieldBehavior;
import view.model.state.CalculatorFieldState;
import view.view.AppComponent;

import javax.swing.*;
import java.util.List;

public class ViewModelImpl {

    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;
    private final FieldBehavior fieldBehavior;
    private CalculatorDataObserver calculatorData;

    public ViewModelImpl() {
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
        fieldBehavior = new FieldBehavior(this);
        calculatorData = new CalculatorData();
    }

    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        JComboBox<String> comboBox = menuSelectable.getParent();
        new MenuListModel(receiveMenu, comboBox);
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }
///////////////////////////////////////////////////////////////////////////
    public void setNotWidthState() {
        fieldState.setState(fieldState.getNotWidthState());
        fieldState.selectMenu();
    }

    public void setWidthState() {
        fieldState.setState(fieldState.getHaveWidthState());
        fieldState.selectMenu();
    }

    public void setAllFieldOffState() {
        fieldState.setState(fieldState.getAllFieldOffState());
        fieldState.actionState();
    }

    public void actionState() {
        fieldState.actionState();
    }

    public void setField(AppComponent component) {
        fieldState.setField(component);
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }
///////////////////////////////////////////////////////////////////////////////////////////////

    public void fieldActivate(AppComponent fieldSelectable) {
        fieldBehavior.fieldActivate(fieldSelectable);
    }

    public void fieldDeactivate(AppComponent fieldSelectable) {
        fieldBehavior.fieldDeactivate(fieldSelectable);
    }

    public void areaActivate(AppComponent fieldSelectable){
        fieldBehavior.areaActivate(fieldSelectable);
    }

    public void addData(AppComponent component) {
        calculatorData.addData(component);
        fieldBehavior.add(calculatorData);
        // TODO set model
    }

    public void addAreaData(AppComponent component) {
        calculatorData.addData(component);
        calculatorData.setAreaStatus(true);
        fieldBehavior.add(calculatorData);
        // TODO set model
    }
}
