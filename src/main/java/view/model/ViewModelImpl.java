package view.model;

import view.MenuListReceivable;
import view.controller.*;

import java.awt.event.KeyEvent;
import java.util.List;

public class ViewModelImpl {

    private final MenuListReceivable menuListReceivable;
    private final FieldKeyBehavior fieldKeyBehavior;
    private final FieldFocusBehavior fieldFocusBehavior;
    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;

    public ViewModelImpl(MenuListReceivable menuListReceivable) {
        this.menuListReceivable = menuListReceivable;
        fieldKeyBehavior = new FieldKeyBehavior(this);
        fieldFocusBehavior = new FieldFocusBehavior();
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
    }

    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenuList = menuSelectable.receiveMenu(selectedItem);
        MenuModel menuModel = new MenuModel(menuSelectable, receiveMenuList);
        menuModel.createMenu();
    }
///////////////////////////////////////////////////////////////////////////////////////////
    public void fieldActivate(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldActivate(fieldSelectable);
        fieldKeyBehavior.fieldActivate(fieldSelectable);
    }

    public void fieldDeactivate(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldDeactivate(fieldSelectable);
        fieldKeyBehavior.fieldDeactivate(fieldSelectable);
    }

    public void fieldFocusGained(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldFocusGained(fieldSelectable);
    }

    public void keyPressed(KeyEvent event) {
        fieldKeyBehavior.keyPressed(event);
    }

    public void keyReleased(KeyEvent event) {
        fieldKeyBehavior.keyReleased(event);
    }
///////////////////////////////////////////////////////////////////////////////
    public Visitor getVisitor() {
        return colorVisitor;
    }

    public void setNotWidthState() {
        fieldState.setState(fieldState.getNotWidthState());
        fieldState.selectMenu();
    }

    public void setWidthState() {
        fieldState.setState(fieldState.getHaveWidthState());
        fieldState.selectMenu();
    }

    public void setAllFieldOffState() {
        fieldState.setState(fieldState.getAllFieldOffState());
        fieldState.selectMenu();
    }
    ////////////////////////////////////////////////////////////////////////////

    public void actionState() {
        fieldState.turnNumbers();
    }

    public void setField(FieldSelectable fieldSelectable) {
        fieldState.setField(fieldSelectable);
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    public MenuListReceivable getMenuReceiver() {
        return menuListReceivable;
    }

    public void keyPressedValue(String text) {

    }

}
