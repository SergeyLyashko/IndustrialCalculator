package model;

import controller.MessageObserver;
import controller.ResultObserver;
import model.detailmass.CalculatorMassFactory;

import java.util.Queue;

public interface CalculatorModel {

    void setDetailData(Queue<String> detailData);

    void setIsArea(boolean status);

    void accept(CalculatorMassFactory massFactory);

    //void registerObserver(ResultObserver observer);

    //void registerObserver(MessageObserver observer);

}
