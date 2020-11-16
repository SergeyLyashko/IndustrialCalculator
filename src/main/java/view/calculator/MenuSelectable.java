package view.calculator;

import view.AppComponent;
import view.MenuReceiver;
import view.calculator.menuboxes.MenuModel;

import java.util.List;

public interface MenuSelectable {

    String getCurrentMenu();

    void actionMenu(String currentMenu);

    List<String> receiveMenu(MenuReceiver menuReceiver);

    String getHeaderMenu();

    void setModel(MenuModel menuModel);

    AppComponent getMenu();
}
