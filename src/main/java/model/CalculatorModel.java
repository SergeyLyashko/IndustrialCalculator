package model;

import controller.MessageObserver;
import controller.ResultObserver;

import java.util.Queue;

public interface CalculatorModel {

    void setDetailData(Queue<String> detailData);

    void setDetailValues(Queue<String> values);

    //void registerObserver(ResultObserver observer);

    //void registerObserver(MessageObserver observer);

}
