package view.viewmodel;

import view.Controller;
import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;
import view.fieldsbehavior.FieldBehaviorImpl;

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

    public Visitor getVisitor() {
        return colorVisitor;
    }

    public void setWidthField(AppComponent component) {
        this.width = component;
        fieldState.setWidthBehavior(new FieldBehaviorImpl(width));
    }

    public void setLengthField(AppComponent component) {
        this.length = component;
        Behavior lengthBehavior = new FieldBehaviorImpl(length);
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

    @Override
    public void keyActionUpdate() {
        CalculatorData newData = createNewData();
        Queue<String> dataList = newData.getData();
        appController.setData(dataList);
    }

    private CalculatorData createNewData() {
        return new CalculatorData(assortment, type, number, width, length, fieldState);
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
