package view.model;

import view.MenuListReceivable;
import view.controller.*;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ViewModelImpl implements ViewModel {

    private final MenuListReceivable menuListReceivable;
    private final FieldKeyBehavior fieldKeyBehavior;
    private final FieldFocusBehavior fieldFocusBehavior;
    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;
    private final Queue<String> fieldValues;

    public ViewModelImpl(MenuListReceivable menuListReceivable) {
        this.menuListReceivable = menuListReceivable;
        fieldKeyBehavior = new FieldKeyBehavior(this);
        fieldFocusBehavior = new FieldFocusBehavior();
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState();
        fieldValues = new LinkedList<>();
    }

    @Override
    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenuList = menuSelectable.receiveMenu(selectedItem);
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
    public void setNotWidthState() {
        fieldState.setState(fieldState.getNotWidthState());
        fieldState.selectMenu();
    }

    @Override
    public void setWidthState() {
        fieldState.setState(fieldState.getHaveWidthState());
        fieldState.selectMenu();
    }

    @Override
    public void actionState() {
        fieldState.turnNumbers();
    }

    @Override
    public void setField(FieldSelectable fieldSelectable) {
        fieldState.setField(fieldSelectable);
    }

    @Override
    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    @Override
    public MenuListReceivable getMenuReceiver() {
        return menuListReceivable;
    }

    @Override
    public void keyPressedValue(String text) {
        fieldValues.add(text);
    }

    @Override
    public Queue<String> getFieldValues() {
        return fieldValues;
    }
}
