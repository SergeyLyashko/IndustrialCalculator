package controller;

import java.util.Queue;

public interface DataValueParser {

    double[] parseData(Queue<String> data);
}
