package view.controller;

import view.view.AppComponent;

public interface MenuSelectable extends AppComponent {

    void receiveMenu(String menuItem);

    default void addChildMenu(MenuSelectable child){}
}
