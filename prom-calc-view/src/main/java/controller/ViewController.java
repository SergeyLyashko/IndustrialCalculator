package controller;

import ui.UiComponent;
import ui.MenuSelectable;

import java.util.List;
import java.util.Map;

public interface ViewController {

    void createMenu(List<String> menuList, MenuSelectable menuSelectable);

    void fieldsOff();

    void widthOn();

    void action();

    void areaCheckBoxState(boolean state);

    void addSelectedItems(Map<String, String> queueItems);

    void setResult(String value, boolean alert);

    void setMessage(String message, boolean alert);

    boolean isWidth();

    boolean isArea();

    List<UiComponent> loadComponents();

    void savedPreference(List<UiComponent> components);

    void setToolTipState(boolean selected);

    void keyActionUpdate();
}
