package viewcontroller;

import controller.CalculatorData;
import view.AppComponent;
import view.Controller;
import view.MenuSelectable;
import view.ViewController;
import view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewControllerImpl implements ViewController, KeyActionObserver {

    private final ViewModel viewModelImpl;
    private final Controller appController;
    private LabelBehavior messageBehavior;
    private LabelBehavior resultBehavior;
    private FieldBehavior widthBehavior;
    private FieldBehavior lengthBehavior;
    private AppComponent width;
    private AppComponent length;
    private Queue<String> queueItems;
    private boolean checkBoxSelected;
    private boolean areaStatus;
    private boolean widthStatus;

    public ViewControllerImpl(ViewModel viewModelImpl, Controller controller){
        this.viewModelImpl = viewModelImpl;
        this.appController = controller;
    }

    @Override
    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        viewModelImpl.createMenu(receiveMenu, menuSelectable);
    }

    @Override
    public void setResultComponent(AppComponent component) {
        this.resultBehavior = viewModelImpl.getLabelBehavior(component);
    }

    @Override
    public void setMessageComponent(AppComponent component) {
        this.messageBehavior = viewModelImpl.getLabelBehavior(component);
    }

    @Override
    public Visitor getVisitor() {
        return viewModelImpl.getVisitor();
    }

    @Override
    public void fieldsOff() {
        resetServiceString();
        widthBehavior.fieldDeactivate();
        lengthBehavior.fieldDeactivate();
        widthStatus = false;
    }

    @Override
    public void widthOn() {
        widthStatus = true;
    }

    @Override
    public void action() {
        resetServiceString();
        lengthBehavior.fieldActivate();
        if(widthStatus){
            checkSelected();
        }
    }

    private void checkSelected(){
        if(checkBoxSelected){
            deactivate();
        }else {
            activate();
        }
    }

    private void activate() {
        widthBehavior.fieldActivate();
        areaDeactivate();
    }

    private void deactivate() {
        widthBehavior.fieldDeactivate();
        areaActivate();
    }

    private void areaActivate(){
        lengthBehavior.areaActivate();
        areaStatus = true;
    }

    private void areaDeactivate(){
        lengthBehavior.areaDeactivate();
        areaStatus = false;
    }

    @Override
    public void areaCheckBoxState(boolean state) {
        this.checkBoxSelected = state;
        action();
    }

    public boolean isArea(){
        return areaStatus;
    }

    public boolean isWidth(){
        return widthStatus;
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    @Override
    public void setWidth(AppComponent component) {
        this.width = component;
        this.widthBehavior = viewModelImpl.getFieldBehavior(component);
        widthBehavior.fieldDeactivate();
    }

    @Override
    public void setLength(AppComponent component) {
        this.length = component;
        this.lengthBehavior = viewModelImpl.getFieldBehavior(component);
        lengthBehavior.fieldDeactivate();
        lengthBehavior.registerObserver(this);
    }

    @Override
    public void setSelectedItems(Queue<String> queueItems) {
        this.queueItems = queueItems;
    }

    @Override
    public void setResult(String result, boolean alert) {
        String value = result;
        if(!alert){
            value = result+" кг";
        }
        resultBehavior.show(value, alert);
    }

    @Override
    public void setMessage(String message, boolean alert) {
        messageBehavior.show(message, alert);
    }

    @Override
    public void keyActionUpdate() {
        CalculatorData data = viewModelImpl.getData(queueItems, width, length, this);
        appController.setData(data);
    }
}
