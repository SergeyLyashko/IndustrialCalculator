package view.model;

import view.view.AppComponent;

public interface CalculatorDataObserver {

    void keyActionUpdate();

    void setAreaStatus(boolean status);

    void addData(AppComponent component);

    void addData(String data);
}
