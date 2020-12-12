package view.viewmodel;

import view.Controller;
import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewModel implements KeyActionObserver{

    private final Visitor colorVisitor;
    private final Controller appController;

    private final Behavior behavior;
    private LabelBehaviorImpl resultBehavior;
    private LabelBehaviorImpl messageBehavior;

    private AppComponent width;
    private AppComponent length;

    private Queue<String> queueItems;

    public ViewModel(Controller appController, Behavior behavior) {
        this.appController = appController;
        colorVisitor = new ColorChangeVisitor();
        this.behavior = behavior;
        behavior.registerObserver(this);
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }

    public void setWidthField(AppComponent component) {
        this.width = component;
        behavior.setWidth(width);
    }

    public void setLengthField(AppComponent component) {
        this.length = component;
        behavior.setLength(length);
    }

    public void setAllFieldOffState() {
        resetServiceString();
        behavior.fieldsOff();
    }

    // активация Number box
    public void actionState() {
        resetServiceString();
        behavior.action();
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    public void setWidthOnState() {
        behavior.widthOn();
    }

    public void checkBoxSelect(boolean state) {
        behavior.checkBoxSelect(state);
    }

    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        new MenuListModel(receiveMenu, menuSelectable);
    }

    public void setResultComponent(AppComponent component) {
        resultBehavior = new LabelBehaviorImpl(colorVisitor, component);
    }

    public void setMessageComponent(AppComponent component) {
        messageBehavior = new LabelBehaviorImpl(colorVisitor, component);
    }

    @Override
    public void keyActionUpdate() {
        appController.setData(new CalculatorDataImpl(queueItems, width, length, behavior));
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

    public void setSelectedItems(Queue<String> queueItems) {
        this.queueItems = queueItems;
    }
}
