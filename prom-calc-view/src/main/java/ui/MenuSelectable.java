package ui;

import controller.ViewController;
import database.DataReceiver;
import ui.calculator.MenuBox;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public interface MenuSelectable extends UiComponent {

    //String DEFAULT_MENU_VALUE = "";
    //String NOT_DATABASE_MESSAGE = "Отсутствует соединение с БД";
    //String ERROR = "error";

    //void setMenuItems(String...menuItem);

    //default void addMenuSelectListener(MenuSelectable listener){}

    JComboBox<String> getComponentParent();

    String getSelectedItem();

    void addActionListener(ActionListener actionListener);

    void addMouseListener(MouseListener mouseListener);

}
