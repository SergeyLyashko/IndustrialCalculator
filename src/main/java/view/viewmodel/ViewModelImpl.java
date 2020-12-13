package view.viewmodel;

import view.Controller;
import view.AppComponent;
import view.MenuSelectable;

import java.util.List;
import java.util.Queue;

public class ViewModelImpl implements KeyActionObserver{

    private final Controller appController;

    private final Behavior behavior;
    private LabelBehavior resultBehavior;
    private LabelBehavior messageBehavior;

    private AppComponent width;
    private AppComponent length;

    private Queue<String> queueItems;

    public ViewModelImpl(Controller appController, Behavior behavior) {
        this.appController = appController;
        this.behavior = behavior;
        behavior.registerObserver(this);
    }

    public void setWidthField(AppComponent component) {
        this.width = component;
        behavior.setWidth(width);
    }

    public void setLengthField(AppComponent component) {
        this.length = component;
        behavior.setLength(length);
    }

    public void fieldsOff() {
        resetServiceString();
        behavior.fieldsOff();
    }

    // активация Number box
    public void action() {
        resetServiceString();
        behavior.action();
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    public void widthOn() {
        behavior.widthOn();
    }

    public void checkBoxSelect(boolean state) {
        behavior.checkBoxSelect(state);
    }

    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        new MenuListModel(receiveMenu, menuSelectable);
    }

    public void setResultComponent(LabelBehavior behavior) {
        resultBehavior = behavior;
    }

    public void setMessageComponent(LabelBehavior behavior) {
        messageBehavior = behavior;
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
