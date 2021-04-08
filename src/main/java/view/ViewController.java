package view;

import viewmodel.KeyActionObserver;

import java.util.List;
import java.util.Queue;

public interface ViewController extends KeyActionObserver {

    void createMenu(List<String> menuList, MenuSelectable menuSelectable);

    void fieldsOff();

    void widthOn();

    void action();

    void areaCheckBoxState(boolean state);

    void addSelectedItems(Queue<String> queueItems);

    void setResult(String value, boolean alert);

    void setMessage(String message, boolean alert);

    boolean isWidth();

    boolean isArea();

    List<AppComponent> loadComponents();

    void savedPreference(List<AppComponent> components);

    void setToolTipState(boolean selected);
}
