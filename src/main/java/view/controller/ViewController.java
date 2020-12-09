package view.controller;

import view.view.AppComponent;
import java.util.List;

public interface ViewController {

    void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable);

    Visitor getVisitor();

    void setAllFieldOffState();

    void setWidthOnState();

    void actionState();

    void areaCheckBoxState(boolean state);

    void setWidth(AppComponent component);

    void setLength(AppComponent component);

    void setParameters(String assortment, String type, String selectedItem);

    void setResultComponent(AppComponent component);

    void setResult(double result);

    void setMessage(String message, boolean alert);

    void setMessageComponent(AppComponent component);
}
