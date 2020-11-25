package view.controller;

import view.model.MenuModel;

import java.util.List;

public interface MenuSelectable extends AppComponent {

    List<String> receiveMenu(String menuItem);

    String getHeaderMenu();

    void setMenuModel(MenuModel menuModel);

    void addReceiver(MenuReceivable menuReceivable);
}
