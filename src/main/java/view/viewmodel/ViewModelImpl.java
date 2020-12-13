package view.viewmodel;

import view.Controller;
import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;
import viewcontroller.LabelBehavior;

import java.util.List;
import java.util.Queue;

public class ViewModelImpl implements KeyActionObserver{

    private final Controller appController;

    private final Behavior behavior;

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
        behavior.fieldsOff();
    }

    // активация Number box
    public void action() {
        behavior.action();
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

    @Override
    public void keyActionUpdate() {
        appController.setData(new CalculatorDataImpl(queueItems, width, length, behavior));
    }

    public void setSelectedItems(Queue<String> queueItems) {
        this.queueItems = queueItems;
    }

    public LabelBehavior getBehavior(Visitor colorVisitor, AppComponent component) {
        return new LabelBehaviorImpl(colorVisitor, component);
    }
}
