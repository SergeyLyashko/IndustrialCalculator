package view.view;

import javax.swing.*;

public interface MenuSelectable extends AppComponent {

    void createMenu(String...menuItem);

    default void addListenerMenu(MenuSelectable child){}

    JComboBox<String> getParent();
}
