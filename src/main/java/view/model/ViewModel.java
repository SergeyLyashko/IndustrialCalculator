package view.model;

import view.controller.*;

import java.awt.event.KeyEvent;
import java.util.List;

public class ViewModel implements ViewModelInterface {

    private final MenuReceivable menuReceivable;
    private final FieldKeyBehavior fieldKeyBehavior;
    private final FieldFocusBehavior fieldFocusBehavior;
    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;

    public ViewModel(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
        fieldKeyBehavior = new FieldKeyBehavior();
        fieldFocusBehavior = new FieldFocusBehavior();
        colorVisitor = new ColorVisitorImpl();
        fieldState = new CalculatorFieldState();
    }

    @Override
    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenuList = menuSelectable.receiveMenu(menuReceivable, selectedItem);
        MenuModel menuModel = new MenuModel(menuSelectable, receiveMenuList);
        menuModel.createMenu();
    }

    @Override
    public void fieldActivate(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldActivate(fieldSelectable);
        fieldKeyBehavior.fieldActivate(fieldSelectable);
    }

    @Override
    public void fieldDeactivate(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldDeactivate(fieldSelectable);
        fieldKeyBehavior.fieldDeactivate(fieldSelectable);
    }

    @Override
    public void fieldFocusGained(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldFocusGained(fieldSelectable);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        fieldKeyBehavior.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        fieldKeyBehavior.keyReleased(event);
    }


    @Override
    public Visitor createVisitor() {
        return colorVisitor;
    }

    @Override
    public void setNotWidthState(MenuSelectable menuSelectable) {
        fieldState.setState(fieldState.getNotWidthState());
        fieldState.selectMenu();
    }

    @Override
    public void setWidthState(MenuSelectable menuSelectable) {
        fieldState.setState(fieldState.getHaveWidthState());
        fieldState.selectMenu();
    }

    @Override
    public void actionState(MenuSelectable menuSelectable) {
        fieldState.turnNumbers();
    }

    @Override
    public void setStateTarget(FieldSelectable fieldSelectable) {
        fieldState.setStateTarget(fieldSelectable);
    }

    @Override
    public void checkBoxSelect() {
        fieldState.checkBoxSelect(true);
    }

    @Override
    public void checkBoxDeselect() {
        fieldState.checkBoxSelect(false);
    }


}
