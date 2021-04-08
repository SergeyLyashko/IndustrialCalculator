package controller;

import java.util.Queue;

public interface CalculatorData {

    Queue<String> getData();

    void add(Queue<String> selectedMenuItems);
}
