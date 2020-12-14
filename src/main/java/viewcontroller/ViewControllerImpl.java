package viewcontroller;

import controller.CalculatorData;
import view.AppComponent;
import view.MenuSelectable;
import view.ViewController;
import view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewControllerImpl implements ViewController, KeyActionObserver, FocusActionObserver {

    private final ViewModel viewModel;
    private final Controller appController;
    private LabelBehavior messageBehavior;
    private LabelBehavior resultBehavior;
    private FieldBehavior widthFieldBehavior;
    private FieldBehavior lengthBehavior;
    private AppComponent width;
    private AppComponent length;
    private Queue<String> queueItems;
    private boolean checkBoxSelected;
    private boolean areaStatus;
    private boolean widthStatus;
    private FocusBehavior widthFocusBehaviorImpl;
    private FocusBehavior lengthFocusBehaviorImpl;
    private KeyBehavior widthKeyBehaviorImpl;
    private KeyBehavior lengthKeyBehaviorImpl;

    public ViewControllerImpl(ViewModel viewModel, Controller controller){
        this.viewModel = viewModel;
        this.appController = controller;
    }

    @Override
    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        viewModel.createMenu(receiveMenu, menuSelectable);
    }

    @Override
    public void setResultComponent(AppComponent component) {
        this.resultBehavior = viewModel.getLabelBehavior(component);
    }

    @Override
    public void setMessageComponent(AppComponent component) {
        this.messageBehavior = viewModel.getLabelBehavior(component);
    }

    @Override
    public Visitor getVisitor() {
        return viewModel.getVisitor();
    }

    @Override
    public void widthOn() {
        widthStatus = true;
    }

    private void checkSelected(){
        if(checkBoxSelected){
            deactivate();
        }else {
            activate();
        }
    }

    @Override
    public void areaCheckBoxState(boolean state) {
        this.checkBoxSelected = state;
        action();
    }

    @Override
    public boolean isArea(){
        return areaStatus;
    }

    @Override
    public boolean isWidth(){
        return widthStatus;
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    private void removeFilter(AppComponent component){
        Filter defaultFilter = viewModel.getDefaultFilter();
        defaultFilter.setFilter(component);
    }

    private void setFilter(AppComponent component){
        Filter digitalFilter = viewModel.getDigitalFilter();
        digitalFilter.setFilter(component);
    }

    @Override
    public void fieldsOff() {
        resetServiceString();
        //
        removeFilter(width);
        widthFieldBehavior.fieldDeactivate();
        widthFocusBehaviorImpl.deactivate();
        widthKeyBehaviorImpl.fieldDeactivate();
        //
        removeFilter(length);
        lengthBehavior.fieldDeactivate();
        lengthFocusBehaviorImpl.deactivate();
        lengthKeyBehaviorImpl.fieldDeactivate();

        widthStatus = false;
    }

    @Override
    public void action() {
        resetServiceString();
        //
        removeFilter(length);
        lengthBehavior.fieldActivate();
        lengthFocusBehaviorImpl.activate();
        //
        if(widthStatus){
            checkSelected();
        }
    }

    private void activate() {
        removeFilter(width);
        widthFieldBehavior.fieldActivate();
        widthFocusBehaviorImpl.activate();
        areaDeactivate();
    }

    private void deactivate() {
        widthFieldBehavior.fieldDeactivate();
        widthFocusBehaviorImpl.deactivate();
        widthKeyBehaviorImpl.fieldDeactivate();

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
    public void setWidth(AppComponent component) {
        this.width = component;
        this.widthFieldBehavior = viewModel.getFieldBehavior(component);
        this.widthFocusBehaviorImpl = viewModel.getFocusBehavior(component);
        this.widthKeyBehaviorImpl = viewModel.getKeyBehavior(component);
        // TODO FOCUS observer
        widthFocusBehaviorImpl.registerFocusObserver(this);
        //
        widthFieldBehavior.fieldDeactivate();
        widthFocusBehaviorImpl.deactivate();
        widthKeyBehaviorImpl.fieldDeactivate();
    }

    @Override
    public void setLength(AppComponent component) {
        this.length = component;
        this.lengthBehavior = viewModel.getFieldBehavior(component);
        this.lengthFocusBehaviorImpl = viewModel.getFocusBehavior(component);
        this.lengthKeyBehaviorImpl = viewModel.getKeyBehavior(component);
        // TODO KEY observer - нажатия наблюдает только длина
        lengthKeyBehaviorImpl.registerKeyObserver(this);
        // TODO FOCUS observer
        lengthFocusBehaviorImpl.registerFocusObserver(this);
        //
        lengthBehavior.fieldDeactivate();
        lengthFocusBehaviorImpl.deactivate();
        lengthKeyBehaviorImpl.fieldDeactivate();
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
        CalculatorData data = viewModel.getData(queueItems, width, length, this);
        appController.setData(data);
    }

    @Override
    public void focusActionUpdate(AppComponent component) {
        setFilter(component);
        widthKeyBehaviorImpl.fieldActivate();
        lengthKeyBehaviorImpl.fieldActivate();
    }
}
