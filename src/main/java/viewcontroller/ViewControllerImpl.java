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
    private FieldsAction lengthAction;
    private FieldsAction widthAction;

    private LabelBehavior messageBehavior;
    private LabelBehavior resultBehavior;
    private AppComponent width;
    private AppComponent length;
    private Queue<String> queueItems;
    private boolean checkBoxSelected;
    private boolean areaStatus;
    private boolean widthStatus;


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
        removeFilter(width);
        removeFilter(length);
        widthAction.deactivate();
        lengthAction.deactivate();
        widthStatus = false;
    }

    @Override
    public void action() {
        resetServiceString();
        removeFilter(length);
        lengthAction.activate();
        if(widthStatus){
            checkSelected();
        }
    }

    private void activate() {
        removeFilter(width);
        widthAction.activate();
        areaDeactivate();
    }

    private void deactivate() {
        widthAction.deactivate();
        areaActivate();
    }

    private void areaActivate(){
        lengthAction.areaActivate();
        areaStatus = true;
    }

    private void areaDeactivate(){
        lengthAction.areaDeactivate();
        areaStatus = false;
    }

    @Override
    public void setWidth(AppComponent component) {
        this.width = component;
        widthAction = new FieldsAction(viewModel, component);
        widthAction.registerFocusObserver(this);
        widthAction.deactivate();
    }

    @Override
    public void setLength(AppComponent component) {
        this.length = component;
        lengthAction = new FieldsAction(viewModel, component);
        lengthAction.registerFocusObserver(this);
        lengthAction.registerKeyObserver(this);
        lengthAction.deactivate();
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
        widthAction.keyActivate();
        lengthAction.keyActivate();
    }
}
