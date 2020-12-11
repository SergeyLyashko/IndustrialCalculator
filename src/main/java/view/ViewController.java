package view;

import java.util.List;

public interface ViewController {

    void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable);

    Visitor getVisitor();

    void fieldsOff();

    void widthOn();

    void action();

    void areaCheckBoxState(boolean state);

    void setWidth(AppComponent component);

    void setLength(AppComponent component);

    void setParameters(String assortment, String type, String selectedItem);

    void setResultComponent(AppComponent component);

    void setResult(String value, boolean alert);

    void setMessage(String message, boolean alert);

    void setMessageComponent(AppComponent component);
}
