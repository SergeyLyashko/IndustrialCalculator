package viewcontroller;

import view.ViewController;
import view.viewmodel.ViewModelImpl;
import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewControllerImpl implements ViewController {

    private final ViewModelImpl viewModelImpl;
    private final Visitor colorVisitor;
    private LabelBehavior messageBehavior;
    private LabelBehavior resultBehavior;

    public ViewControllerImpl(ViewModelImpl viewModelImpl, Visitor colorVisitor){
        this.viewModelImpl = viewModelImpl;
        this.colorVisitor = colorVisitor;
    }

    @Override
    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        viewModelImpl.createMenu(receiveMenu, menuSelectable);
    }

    @Override
    public void setResultComponent(AppComponent component) {
        resultBehavior = viewModelImpl.getResultBehavior(colorVisitor, component);
    }

    @Override
    public void setMessageComponent(AppComponent component) {
        messageBehavior = viewModelImpl.getMessageBehavior(colorVisitor, component);
    }

    @Override
    public Visitor getVisitor() {
        return colorVisitor;
    }

    @Override
    public void fieldsOff() {
        resetServiceString();
        viewModelImpl.fieldsOff();
    }

    @Override
    public void widthOn() {
        viewModelImpl.widthOn();
    }

    @Override
    public void action() {
        resetServiceString();
        viewModelImpl.action();
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    @Override
    public void setWidth(AppComponent component) {
        viewModelImpl.setWidthField(component);
    }

    @Override
    public void areaCheckBoxState(boolean state) {
        viewModelImpl.checkBoxSelect(state);
    }

    @Override
    public void setLength(AppComponent component) {
        viewModelImpl.setLengthField(component);
    }

    @Override
    public void setSelectedItems(Queue<String> queueItems) {
        viewModelImpl.setSelectedItems(queueItems);
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
}
