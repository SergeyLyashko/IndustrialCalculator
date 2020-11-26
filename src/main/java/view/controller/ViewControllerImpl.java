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
    public void activateVisitor(Host host) {
        viewModel.activateVisitor(host);
    }

    @Override
    public void deactivateVisitor(Host host) {
        viewModel.deactivateVisitor(host);
    }
}
