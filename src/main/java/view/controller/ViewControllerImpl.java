package view.controller;

import view.model.ViewModelInterface;

public class ViewControllerImpl implements ViewController {

    private final ViewModelInterface viewModel;

    public ViewControllerImpl(ViewModelInterface viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void selectMenu(MenuSelectable menuSelectable, String selectedItem) {
        viewModel.createMenu(menuSelectable, selectedItem);
    }

    @Override
    public void setWidth() {

    }

    @Override
    public void setLength() {

    }

    @Override
    public void colorThemeSelect() {

    }

    @Override
    public void toolTipsSelect() {

    }

    @Override
    public void complexAreaBoxSelect() {

    }
}
