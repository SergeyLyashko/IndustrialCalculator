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
    public void setStateTarget(FieldSelectable fieldSelectable) {
        viewModel.setField(fieldSelectable);
    }

    @Override
    public void checkBoxSelect(boolean state) {
        viewModel.checkBoxSelect(state);
    }
}