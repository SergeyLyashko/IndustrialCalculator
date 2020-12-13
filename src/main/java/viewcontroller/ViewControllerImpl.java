package viewcontroller;

import view.ViewController;
import view.labelbehavior.LabelBehaviorImpl;
import view.viewmodel.ViewModelImpl;
import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewControllerImpl implements ViewController {

    private final ViewModelImpl viewModelImpl;
    private final Visitor colorVisitor;

    public ViewControllerImpl(ViewModelImpl viewModelImpl, Visitor colorVisitor){
        this.viewModelImpl = viewModelImpl;
        this.colorVisitor = colorVisitor;
    }

    @Override
    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        viewModelImpl.createMenu(receiveMenu, menuSelectable);
    }

    @Override
    public Visitor getVisitor() {
        return colorVisitor;
    }

    @Override
    public void fieldsOff() {
        viewModelImpl.fieldsOff();
    }

    @Override
    public void widthOn() {
        viewModelImpl.widthOn();
    }

    @Override
    public void action() {
        viewModelImpl.action();
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
    public void setResultComponent(AppComponent component) {
        viewModelImpl.setResultComponent(new LabelBehaviorImpl(colorVisitor, component));
    }

    @Override
    public void setMessageComponent(AppComponent component) {
        viewModelImpl.setMessageComponent(new LabelBehaviorImpl(colorVisitor, component));
    }

    @Override
    public void setResult(String value, boolean alert) {
        viewModelImpl.setResult(value, alert);
    }

    @Override
    public void setMessage(String message, boolean alert) {
        viewModelImpl.setMessage(message, alert);
    }
}
