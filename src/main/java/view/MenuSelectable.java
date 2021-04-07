package view;

import javax.swing.*;

public interface MenuSelectable extends AppComponent {

    String DEFAULT_MENU_VALUE = "";
    String NOT_DATABASE_MESSAGE = "Отсутствует соединение с БД";
    String ERROR = "error";

    void receiveMenu(String...menuItem);

    default void addMenuSelectListener(MenuSelectable listener){}

    JComboBox<String> getComponentParent();
}
