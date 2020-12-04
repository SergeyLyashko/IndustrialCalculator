package view.model;

import controller.Controller;
import view.controller.*;
import view.model.behavior.FieldBehavior;
import view.model.state.CalculatorFieldState;
import view.view.AppComponent;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

public class ViewModel implements KeyActionObserver{

    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;
    private final FieldBehavior lengthBehavior;
    private final FieldBehavior widthBehavior;
    private final Controller appController;
    private CalculatorData calculatorData;
    private AppComponent width;
    private AppComponent length;

    public ViewModel(Controller appController) {
        this.appController = appController;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
        widthBehavior = new FieldBehavior();
        lengthBehavior = new FieldBehavior();
        calculatorData = new CalculatorDataImpl();
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

    public void setAllFieldOffState() {
        widthBehavior.fieldDeactivate(width);
        lengthBehavior.fieldDeactivate(length);
        fieldState.setState(fieldState.getAllFieldOffState());
    }

    public void setWidthOnState() {
        fieldState.setState(fieldState.getWidthFieldOnState());
        calculatorData.setWidthStatus(true);
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    public void setData(String param) {
        calculatorData.addData(param);
    }

    // активация Number box
    public void actionState() {
        createData();
        lengthBehavior.fieldActivate(length);
        fieldState.actionState();
    }

    private void createData(){
        calculatorData = new CalculatorDataImpl();
        calculatorData.addData(width);
        calculatorData.addData(length);
        lengthBehavior.registerObserver(calculatorData);
        lengthBehavior.registerObserver(this);
    }

    public void widthDeactivate() {
        widthBehavior.fieldDeactivate(width);
        areaActivate();
    }

    public void widthActivate() {
        widthBehavior.fieldActivate(width);
        areaDeactivate();
        calculatorData.setWidthStatus(true);
    }

    private void areaActivate(){
        lengthBehavior.areaActivate(length);
        calculatorData.setAreaStatus(true);
    }

    private void areaDeactivate(){
        lengthBehavior.areaDeactivate(length);
        calculatorData.setAreaStatus(false);
    }

    @Override
    public void keyActionUpdate() {
        Queue<String> dataList = calculatorData.getData();
        System.out.println("test data: "+dataList);
        appController.setDetailData(dataList);
    }
}
