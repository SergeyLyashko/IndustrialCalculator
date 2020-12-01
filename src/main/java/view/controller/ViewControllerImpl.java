package view.controller;

import view.MenuListReceiver;
import view.model.ViewModelImpl;
import view.view.AppComponent;

public class ViewControllerImpl implements ViewController {

    private final ViewModelImpl viewModel;

    public ViewControllerImpl(ViewModelImpl viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void selectMenu(MenuSelectable menuSelectable, String selectedItem) {
        viewModel.createMenu(menuSelectable, selectedItem);
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
    public void setStateTarget(AppComponent component) {
        viewModel.setField(component);
    }

    @Override
    public void checkBoxSelect(boolean state) {
        viewModel.checkBoxSelect(state);
    }

    @Override
    public MenuListReceiver getMenuReceiver() {
        return viewModel.getMenuReceiver();
    }
}
