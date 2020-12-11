package view.viewcontroller;

import view.AppComponent;
import view.MenuSelectable;
import view.Visitor;

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

    void setResult(String result, boolean alert);

    void setMessage(String message, boolean alert);

    void setMessageComponent(AppComponent component);
}
