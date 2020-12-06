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

    private final FieldBehavior lengthBehavior;
    private final FieldBehavior widthBehavior;
    private final LabelBehavior resultBehavior;
    private final LabelBehavior messageBehavior;

    private AppComponent width;
    private AppComponent length;
    private boolean widthStatus;
    private String assortment;
    private String type;
    private String number;
    private boolean areaStatus;


    public ViewModel(Controller appController) {
        this.appController = appController;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);

        widthBehavior = new FieldBehavior();
        lengthBehavior = new FieldBehavior();
        lengthBehavior.registerObserver(this);

        resultBehavior = new LabelBehavior();
        messageBehavior = new LabelBehavior();
    }

    public void widthActivate() {
        widthBehavior.fieldActivate(width);
        areaDeactivate();
        widthStatus = true;
    }

    private void areaActivate(){
        lengthBehavior.areaActivate(length);
        areaStatus = true;
    }

    private void areaDeactivate(){
        lengthBehavior.areaDeactivate(length);
        areaStatus = false;
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
        widthStatus = true;
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        // TODO убрать в класс ???
        JComboBox<String> comboBox = menuSelectable.getParent();
        new MenuListModel(receiveMenu, comboBox);
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }

    // активация Number box
    public void actionState() {
        lengthBehavior.fieldActivate(length);
        fieldState.actionState();
    }

    public void widthDeactivate() {
        widthBehavior.fieldDeactivate(width);
        areaActivate();
    }

    @Override
    public void keyActionUpdate() {
        CalculatorData newData = createNewData();
        Queue<String> dataList = newData.getData();
        System.out.println("test data: "+dataList);// TODO DEL TEST
        appController.setData(dataList);
    }

    public CalculatorData createNewData() {
        return new CalculatorDataImpl(assortment, type, number, width, length, widthStatus, areaStatus);
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

    public void setAssortment(String assortment) {
        this.assortment = assortment;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
