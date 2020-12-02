package view.controller;

import view.model.ViewModelImpl;
import view.view.AppComponent;

import java.util.List;

public class ViewControllerImpl implements ViewController {

    private final ViewModelImpl viewModel;

    public ViewControllerImpl(ViewModelImpl viewModel){
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
    public void setNotWidthState() {
        viewModel.setNotWidthState();
    }

    @Override
    public void setWidthState() {
        viewModel.setWidthState();
    }

    @Override
    public void actionState() {
        viewModel.actionState();
    }

    @Override
    public void setField(AppComponent component) {
        viewModel.setField(component);
    }

    @Override
    public void checkBoxSelect(boolean state) {
        viewModel.checkBoxSelect(state);
    }
}
