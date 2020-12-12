package view.viewmodel;

import view.AppComponent;

public interface Behavior {

    void setWidth(AppComponent width);

    void setLength(AppComponent length);

    void fieldsOff();

    void action();

    void widthOn();

    void checkBoxSelect(boolean state);

    boolean isWidth();

    boolean isArea();

    void registerObserver(KeyActionObserver observer);
}
