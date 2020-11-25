package view.controller;

import view.model.MenuModel;
import view.view.AppComponent;

import java.util.List;

public interface MenuSelectable extends AppComponent {

    List<String> receiveMenu(MenuReceivable menuReceivable, String menuItem);

    String getHeaderMenu();

    void setMenuModel(MenuModel menuModel);
}
