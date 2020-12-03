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
    private AppComponent width;
    private AppComponent length;

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

    public void setWidthOnState() {
        fieldState.setField(width);
        fieldState.setState(fieldState.getWidthFieldOnState());
    }

    public void setAllFieldOffState() {
        fieldDeactivate(width);
        fieldDeactivate(length);
        fieldState.setState(fieldState.getAllFieldOffState());
    }

    public void actionState() {
        fieldActivate(length);
        fieldState.actionState();
    }

    public void setWidth(AppComponent component) {
        this.width = component;
    }

    public void setLength(AppComponent component) {
        this.length = component;
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    public void fieldDeactivate(AppComponent fieldSelectable) {
        fieldBehavior.fieldDeactivate(fieldSelectable);
    }

    public void fieldActivate(AppComponent fieldSelectable) {
        fieldBehavior.fieldActivate(fieldSelectable);
    }

    public void areaActivate(){
        fieldBehavior.areaActivate(length);
        addAreaData(length);
    }

    public void areaDeactivate(){
        fieldActivate(length);
    }

    private void addAreaData(AppComponent component) {
        calculatorData.addData(component);
        calculatorData.setAreaStatus(true);
        fieldBehavior.add(calculatorData);
        // TODO set model
    }

    private void addData(AppComponent component) {
        calculatorData.addData(component);
        fieldBehavior.add(calculatorData);
        // TODO set model
    }

    public void setParameter(String param) {
        calculatorData.addData(param);
    }
}
