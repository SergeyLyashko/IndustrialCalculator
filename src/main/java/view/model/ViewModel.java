package view.model;

import view.Controller;
import view.view.AppComponent;
import view.view.MenuSelectable;
import view.view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewModel implements KeyActionObserver{

    private final Visitor colorVisitor;
    private final Controller appController;

    private final FieldState fieldState;
    private LabelBehaviorImpl resultBehavior;
    private LabelBehaviorImpl messageBehavior;

    private AppComponent width;
    private AppComponent length;

    private String assortment;
    private String type;
    private String number;

    public ViewModel(Controller appController) {
        this.appController = appController;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new FieldState();
    }

    public void setWidthField(AppComponent component) {
        this.width = component;
        fieldState.setWidthBehavior(new FieldBehavior(width));
    }

    public void setLengthField(AppComponent component) {
        this.length = component;
        FieldBehavior lengthBehavior = new FieldBehavior(length);
        fieldState.setLengthBehavior(lengthBehavior);
        lengthBehavior.registerObserver(this);
    }

    public void setResultComponent(AppComponent component) {
        resultBehavior = new LabelBehaviorImpl(colorVisitor, component);
    }

    public void setMessageComponent(AppComponent component) {
        messageBehavior = new LabelBehaviorImpl(colorVisitor, component);
    }

    public void setAllFieldOffState() {
        resetServiceString();
        fieldState.fieldsOff();
    }

    // активация Number box
    public void actionState() {
        resetServiceString();
        fieldState.action();
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    public void setWidthOnState() {
        fieldState.widthOn();
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

    @Override
    public void keyActionUpdate() {
        CalculatorData newData = createNewData();
        Queue<String> dataList = newData.getData();
        appController.setData(dataList);
    }

    private CalculatorData createNewData() {
        boolean areaStatus = fieldState.isArea();
        boolean widthStatus = fieldState.isWidth();
        return new CalculatorData(assortment, type, number, width, length, widthStatus, areaStatus);
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
