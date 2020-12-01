package view.model;

import view.MenuListReceiver;
import view.controller.*;
import view.view.AppComponent;

import java.util.List;

public class ViewModelImpl {

    private final MenuListReceiver menuListReceiver;
    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;

    public ViewModelImpl(MenuListReceiver menuListReceiver) {
        this.menuListReceiver = menuListReceiver;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
    }

    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenuList = menuSelectable.receiveMenu(selectedItem);
        MenuListModel menuListModel = new MenuListModel(menuSelectable, receiveMenuList);
        menuListModel.createMenu();
    }

    // TODO ???/
    public Visitor getVisitor() {
        return colorVisitor;
    }
///////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////////////////////

    public MenuListReceiver getMenuReceiver() {
        return menuListReceiver;
    }

}
