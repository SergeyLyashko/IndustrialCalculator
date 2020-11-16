package view.calculator.menuboxes;

import view.MenuReceiver;

import java.util.List;

public interface MenuSelectable {

    String getCurrentMenu();

    void actionMenu(String currentMenu);

    List<String> receiveMenu(MenuReceiver menuReceiver);
}
