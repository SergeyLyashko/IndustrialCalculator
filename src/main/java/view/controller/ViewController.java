package view.controller;

import view.view.AppComponent;

import java.util.List;

public interface ViewController {

    void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable);

    Visitor getVisitor();

    void setAllFieldOffState();

    void setWidthState();

    void actionState();

    void setWidth(AppComponent component);

    void checkBoxSelect(boolean state);

    void setLength(AppComponent component);
}
