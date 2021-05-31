package ui;

import javax.swing.*;

public interface MenuSelectable extends UiComponent {

    //String DEFAULT_MENU_VALUE = "";
    //String NOT_DATABASE_MESSAGE = "Отсутствует соединение с БД";
    //String ERROR = "error";

    //void setMenuItems(String...menuItem);

    //default void addMenuSelectListener(MenuSelectable listener){}

    JComboBox<String> getComponentParent();
}
