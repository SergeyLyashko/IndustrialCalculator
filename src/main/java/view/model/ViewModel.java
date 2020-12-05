package view.model;

import controller.Controller;
import view.controller.*;
import view.model.behavior.FieldBehavior;
import view.model.behavior.LabelBehavior;
import view.model.state.CalculatorFieldState;
import view.view.AppComponent;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

public class ViewModel implements KeyActionObserver{

    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;
    private final Controller appController;
    private CalculatorData calculatorData;

    private AppComponent width;
    private AppComponent length;

    private final FieldBehavior lengthBehavior;
    private final FieldBehavior widthBehavior;
    private final LabelBehavior resultBehavior;
    private final LabelBehavior messageBehavior;


    public ViewModel(Controller appController) {
        this.appController = appController;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
        widthBehavior = new FieldBehavior();
        lengthBehavior = new FieldBehavior();
        resultBehavior = new LabelBehavior();
        messageBehavior = new LabelBehavior();

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
        resetField();
        fieldState.setState(fieldState.getAllFieldOffState());
    }

    private void resetField(){
        resultBehavior.reset();
        messageBehavior.reset();
        widthBehavior.fieldDeactivate(width);
        lengthBehavior.fieldDeactivate(length);
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
        System.out.println("test data: "+dataList);// TODO DEL TEST
        appController.setData(dataList);
    }

    public void setResultComponent(AppComponent component) {
        resultBehavior.setComponent(component);
    }

    public void setResult(double result) {
        String value = String.valueOf(result);
        resultBehavior.show(value);
    }

    public void setMessage(String message) {
        messageBehavior.show(message);
    }

    public void setMessageComponent(AppComponent component) {
        messageBehavior.setComponent(component);
    }
}
