package view.model;

import view.MenuListReceivable;
import view.controller.MenuSelectable;
import view.controller.Visitor;
import view.view.AppComponent;

public interface ViewModel {

    void createMenu(MenuSelectable menuSelectable, String selectedItem);

    Visitor getVisitor();

    void setNotWidthState();

    void setWidthState();

    void actionState();

    void setField(AppComponent fieldSelectable);

    void checkBoxSelect(boolean state);

    MenuListReceivable getMenuReceiver();
}
