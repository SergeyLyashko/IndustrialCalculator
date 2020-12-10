package view.model;

import view.Controller;
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

    private final LabelBehavior resultBehavior;
    private final LabelBehavior messageBehavior;

    private AppComponent width;
    private AppComponent length;

    private String assortment;
    private String type;
    private String number;

    public ViewModel(Controller appController) {
        this.appController = appController;
        colorVisitor = new ColorChangeVisitor();

        fieldState = new FieldState(this);

        resultBehavior = new LabelBehavior(colorVisitor);
        messageBehavior = new LabelBehavior(colorVisitor);
    }

    public void setWidthField(AppComponent component) {
        this.width = component;
        fieldState.setWidth(width);
    }

    public void setLengthField(AppComponent component) {
        this.length = component;
        fieldState.setLength(length);
    }

    public void setAllFieldOffState() {
        resetServiceString();
        fieldState.setFieldsOff();
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    public void setWidthOnState() {
        fieldState.setWidthOn();
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
        fieldState.actionState();
    }

    @Override
    public void keyActionUpdate() {
        CalculatorData newData = createNewData();
        Queue<String> dataList = newData.getData();
        appController.setData(dataList);
    }

    public CalculatorData createNewData() {
        boolean areaStatus = fieldState.isArea();
        boolean widthStatus = fieldState.isWidth();
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
}
