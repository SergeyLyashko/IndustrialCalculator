package view.controller;

import view.view.View;

public class ViewControllerImpl implements ViewController {

    private final ViewModelInterface viewModelInterface;
    private View view;

    public ViewControllerImpl(ViewModelInterface viewModelInterface, MenuReceivable menuReceivable){
        this.viewModelInterface = viewModelInterface;
        this.view = new View(menuReceivable);
        view.createView();
    }

    @Override
    public void assortmentSelect() {

    }

    @Override
    public void typeSelect() {

    }

    @Override
    public void numberSelect() {

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
