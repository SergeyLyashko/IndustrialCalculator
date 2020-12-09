package view.model;

import view.Controller;
import view.model.behavior.FieldBehavior;
import view.model.behavior.LabelBehavior;
import view.model.state.FieldState;
import view.view.AppComponent;
import view.view.MenuSelectable;
import view.view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewModel implements KeyActionObserver{

    private final Visitor colorVisitor;
    private final State fieldState;
    private final Controller appController;

    private final FieldBehavior lengthBehavior;
    private final FieldBehavior widthBehavior;
    private final LabelBehavior resultBehavior;
    private final LabelBehavior messageBehavior;

    private AppComponent width;
    private AppComponent length;

    private String assortment;
    private String type;
    private String number;

    private boolean areaStatus;
    private boolean widthStatus;


    public ViewModel(Controller appController) {
        this.appController = appController;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new FieldState(this);

        widthBehavior = new FieldBehavior();
        lengthBehavior = new FieldBehavior();
        lengthBehavior.registerObserver(this);

        resultBehavior = new LabelBehavior(colorVisitor);
        messageBehavior = new LabelBehavior(colorVisitor);
    }
////////////////////////////////////////////////////////////////
    
    public void widthActivate() {
        widthBehavior.fieldActivate(width);
        areaDeactivate();
        widthStatus = true;
    }

    public void widthDeactivate() {
        widthBehavior.fieldDeactivate(width);
        areaActivate();
        widthStatus = false;
    }

    private void areaActivate(){
        lengthBehavior.areaActivate(length);
        areaStatus = true;
    }

    private void areaDeactivate(){
        lengthBehavior.areaDeactivate(length);
        areaStatus = false;
    }
///////////////////////////////////////////////////

    public void setAllFieldOffState() {
        resetField();
        resetServiceString();
        fieldState.setFieldsOff();
    }

    private void resetField(){
        widthBehavior.fieldDeactivate(width);
        lengthBehavior.fieldDeactivate(length);
        widthStatus = false;
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    public void setWidthOnState() {
        fieldState.setWidthOn();
        widthStatus = true;
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        new MenuListModel(receiveMenu, menuSelectable);
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }

    // активация Number box
    public void actionState() {
        resetServiceString();
        lengthBehavior.fieldActivate(length);
        fieldState.actionState();
    }

    @Override
    public void keyActionUpdate() {
        CalculatorData newData = createNewData();
        Queue<String> dataList = newData.getData();
        appController.setData(dataList);
    }

    public CalculatorData createNewData() {
        return new CalculatorDataImpl(assortment, type, number, width, length, widthStatus, areaStatus);
    }

    public void setResultComponent(AppComponent component) {
        resultBehavior.setComponent(component);
    }

    public void setResult(String result, boolean alert) {
        String value = result;
        if(!alert){
            value = result+" кг";
        }
        resultBehavior.show(value, alert);
    }

    public void setMessage(String message, boolean alert) {
        messageBehavior.show(message, alert);
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

    public void setWidthField(AppComponent component) {
        this.width = component;
    }

    public void setLengthField(AppComponent component) {
        this.length = component;
    }
}
