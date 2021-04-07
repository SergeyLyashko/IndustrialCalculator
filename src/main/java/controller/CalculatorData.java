package controller;

import java.util.Queue;

public interface CalculatorData {

    Queue<String> getData();

    void addData(Queue<String> queueItems);
}
