package controller;

import java.util.Queue;

public interface CalculatorModel extends ViewSubject {

    void setData(Queue<String> detailData);

    void calculation();
}
