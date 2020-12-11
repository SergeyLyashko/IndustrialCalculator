package view.viewcontroller;

import view.ViewController;
import view.viewmodel.ViewModel;
import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;

import java.util.List;
import java.util.Queue;

public class ViewControllerImpl implements ViewController {

    private final ViewModel viewModel;

    public ViewControllerImpl(ViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        viewModel.createMenu(receiveMenu, menuSelectable);
    }

    @Override
    public Visitor getVisitor() {
        return viewModel.getVisitor();
    }

    @Override
    public void fieldsOff() {
        viewModel.setAllFieldOffState();
    }

    @Override
    public void widthOn() {
        viewModel.setWidthOnState();
    }

    @Override
    public void action() {
        viewModel.actionState();
    }

    @Override
    public void setWidth(AppComponent component) {
        viewModel.setWidthField(component);
    }

    @Override
    public void areaCheckBoxState(boolean state) {
        viewModel.checkBoxSelect(state);
    }

    @Override
    public void setLength(AppComponent component) {
        viewModel.setLengthField(component);
    }

    @Override
    public void setSelectedItems(Queue<String> queueItems) {
        viewModel.setSelectedItems(queueItems);

    }

    @Override
    public void setResultComponent(AppComponent component) {
        viewModel.setResultComponent(component);
    }

    @Override
    public void setMessageComponent(AppComponent component) {
        viewModel.setMessageComponent(component);
    }

    @Override
    public void setResult(String value, boolean alert) {
        viewModel.setResult(value, alert);
    }

    @Override
    public void setMessage(String message, boolean alert) {
        viewModel.setMessage(message, alert);
    }
}
