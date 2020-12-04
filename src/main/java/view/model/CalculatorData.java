package view.model;

import view.view.AppComponent;

import java.util.Queue;

public interface CalculatorData extends KeyActionObserver {

    void setAreaStatus(boolean status);

    void addData(AppComponent component);

    void addData(String data);

    void setWidthStatus(boolean status);

    Queue<String> getDataList();

    boolean isArea();
}
