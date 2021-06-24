package controller;

import ui.MenuSelectable;

import javax.swing.*;
import java.util.List;

public interface ViewController {

    void createMenu(List<String> menuList, MenuSelectable menuSelectable);

    void fieldsOff();

    void widthCheck();

    void action();

    void setResult(String value, boolean alert);

    void setMessage(String message, boolean alert);

    List<JComponent> loadComponents();

    void savedPreference(List<JComponent> components);

    void setToolTipState(boolean selected);

}
