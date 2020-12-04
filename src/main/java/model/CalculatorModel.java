package model;

import model.detailmass.CalculatorMassFactory;

import java.util.Queue;

public interface CalculatorModel {

    void setDetailData(Queue<String> detailData);

    void accept(CalculatorMassFactory massFactory);

}
