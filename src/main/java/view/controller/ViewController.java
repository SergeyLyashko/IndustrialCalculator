package view.controller;

import view.MenuListReceivable;
import view.view.AppComponent;

public interface ViewController {

    void selectMenu(MenuSelectable menuSelectable, String selectedItem);

    Visitor getVisitor();

    void setAllFieldOffState();

    void setNotWidthState();

    void setWidthState();

    void actionState();

    void setStateTarget(AppComponent fieldSelectable);

    void checkBoxSelect(boolean state);

    MenuListReceivable getMenuReceiver();
}
