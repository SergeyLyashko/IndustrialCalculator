package view.controller;

import view.model.ViewModelInterface;

import java.awt.event.KeyEvent;

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
    public void fieldActivate(FieldSelectable fieldSelectable) {
        viewModel.fieldActivate(fieldSelectable);
    }

    @Override
    public void fieldDeactivate(FieldSelectable fieldSelectable) {
        viewModel.fieldDeactivate(fieldSelectable);
    }


    @Override
    public void fieldFocusGained(FieldSelectable fieldSelectable) {
        viewModel.fieldFocusGained(fieldSelectable);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        viewModel.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        viewModel.keyReleased(event);
    }

    @Override
    public Visitor getVisitor() {
        return viewModel.createVisitor();
    }

    @Override
    public void setNotWidthState(MenuSelectable menuSelectable) {
        viewModel.setNotWidthState(menuSelectable);
    }

    @Override
    public void setWidthState(MenuSelectable menuSelectable) {
        viewModel.setWidthState(menuSelectable);
    }

    @Override
    public void actionState(MenuSelectable menuSelectable) {
        viewModel.actionState(menuSelectable);
    }

    @Override
    public void setStateTarget(FieldSelectable fieldSelectable) {
        viewModel.setStateTarget(fieldSelectable);
    }

    @Override
    public void checkBoxSelect() {
        viewModel.checkBoxSelect();
    }

    @Override
    public void checkBoxDeselect() {
        viewModel.checkBoxDeselect();
    }
}
