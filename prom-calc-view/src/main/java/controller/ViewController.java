package controller;

import ui.UiComponent;
import ui.MenuSelectable;

import java.util.List;

public interface ViewController {

    void createMenu(List<String> menuList, MenuSelectable menuSelectable);

    void fieldsOff();

    void widthOn();

    void action();

    void areaActivate();

    void setResult(String value, boolean alert);

    void setMessage(String message, boolean alert);

    boolean isWidth();

    List<UiComponent> loadComponents();

    void savedPreference(List<UiComponent> components);

    void setToolTipState(boolean selected);

    void areaDeactivate();
}
