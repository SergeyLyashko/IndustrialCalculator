package view.viewcontroller;

import view.viewmodel.ViewModel;
import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;

import java.util.List;

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
    public void setAllFieldOffState() {
        viewModel.setAllFieldOffState();
    }

    @Override
    public void setWidthOnState() {
        viewModel.setWidthOnState();
    }

    @Override
    public void actionState() {
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
    public void setParameters(String assortment, String type, String number) {
        viewModel.setAssortment(assortment);
        viewModel.setType(type);
        viewModel.setNumber(number);
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
    public void setResult(String result, boolean alert) {
        viewModel.setResult(result, alert);
    }

    @Override
    public void setMessage(String message, boolean alert) {
        viewModel.setMessage(message, alert);
    }
}
