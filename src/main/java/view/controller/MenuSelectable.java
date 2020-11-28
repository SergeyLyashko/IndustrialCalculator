package view.controller;

import view.view.AppComponent;

import java.util.List;

public interface MenuSelectable extends AppComponent {

    List<String> receiveMenu(String menuItem);

    String getHeaderMenu();

    default void addChildMenu(MenuSelectable child){}
}
