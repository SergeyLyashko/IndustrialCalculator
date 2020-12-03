package view.model;

import view.view.AppComponent;

public interface CalculatorData {

    void keyActionUpdate();

    void setAreaStatus(boolean status);

    void addData(AppComponent component);

    void addData(String data);

    void setWidthStatus(boolean status);
}
