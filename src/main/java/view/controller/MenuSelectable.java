package view.controller;

import view.view.AppComponent;

import javax.swing.*;

public interface MenuSelectable extends AppComponent {

    void receiveMenu(String...menuItem);

    default void addChildMenu(MenuSelectable child){}

    JComboBox<String> getParent();
}
