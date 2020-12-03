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
    private CalculatorData calculatorData;
    private AppComponent width;
    private AppComponent length;

    public ViewModelImpl() {
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
        fieldBehavior = new FieldBehavior(this);
    }

    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        JComboBox<String> comboBox = menuSelectable.getParent();
        new MenuListModel(receiveMenu, comboBox);
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }

    public void setWidthField(AppComponent component) {
        this.width = component;
    }

    public void setLengthField(AppComponent component) {
        this.length = component;
    }

    public void setData(String param) {
        calculatorData.addData(param);
    }

    public void setAllFieldOffState() {
        fieldBehavior.fieldDeactivate(width);
        fieldBehavior.fieldDeactivate(length);
        fieldState.setState(fieldState.getAllFieldOffState());
    }

    public void setWidthOnState() {
        fieldState.setState(fieldState.getWidthFieldOnState());
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    // активация Number box
    public void actionState() {
        createData();
        fieldBehavior.fieldActivate(length);
        fieldState.actionState();
    }

    // TODO ???? инициализацию Data перенести в конструктор ??
    private void createData(){
        calculatorData = new CalculatorDataImpl();
        calculatorData.addData(width);
        calculatorData.addData(length);
        fieldBehavior.registerObserver(calculatorData);
    }

    public void widthDeactivate() {
        fieldBehavior.fieldDeactivate(width);
        areaActivate();
    }

    public void widthActivate() {
        fieldBehavior.fieldActivate(width);
        areaDeactivate();
        calculatorData.setWidthStatus(true);
    }

    private void areaActivate(){
        fieldBehavior.areaActivate(length);
        calculatorData.setAreaStatus(true);
    }

    private void areaDeactivate(){
        fieldBehavior.fieldActivate(length);
        calculatorData.setAreaStatus(false);
    }
}
