package view.model;

import view.MenuListReceivable;
import view.controller.*;
import view.view.AppComponent;

import java.util.List;

public class ViewModelImpl {

    private final MenuListReceivable menuListReceivable;
    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;

    public ViewModelImpl(MenuListReceivable menuListReceivable) {
        this.menuListReceivable = menuListReceivable;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
    }

    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenuList = menuSelectable.receiveMenu(selectedItem);
        MenuModel menuModel = new MenuModel(menuSelectable, receiveMenuList);
        menuModel.createMenu();
    }

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
        fieldState.turnNumbers();
    }

    public void actionState() {
        fieldState.turnNumbers();
    }

    public void setField(AppComponent fieldSelectable) {
        fieldState.setField(fieldSelectable);
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }

    public MenuListReceivable getMenuReceiver() {
        return menuListReceivable;
    }

}
