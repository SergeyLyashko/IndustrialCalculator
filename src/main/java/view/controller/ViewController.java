package view.controller;

import view.view.AppComponent;

import java.util.List;

public interface ViewController {

    void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable);

    Visitor getVisitor();

    void setAllFieldOffState();

    void setNotWidthState();

    void setWidthState();

    void actionState();

    void setStateTarget(AppComponent component);

    void checkBoxSelect(boolean state);

}
