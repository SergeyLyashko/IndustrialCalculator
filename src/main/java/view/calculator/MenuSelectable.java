package view.calculator;

import view.AppComponent;
import view.MenuReceiver;
import view.Visitor;
import view.calculator.menuboxes.MenuModel;

import java.util.List;

public interface MenuSelectable extends AppComponent {


    List<String> receiveMenu(String menuItem);

    String getHeaderMenu();

    void setModel(MenuModel menuModel);

    AppComponent getMenu();

    void addListener(MenuSelectable menuSelectable);

    default void addListener(Visitor visitor){}

    void addReceiver(MenuReceiver menuReceiver);
}
