package view.controller;

import view.model.ViewModel;
import view.view.AppComponent;

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
        viewModel.setData(assortment);
        viewModel.setData(type);
        viewModel.setData(number);
    }
}
