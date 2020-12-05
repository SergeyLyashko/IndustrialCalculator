package model;

import model.detailmass.CalculatorMassFactory;

import java.util.Queue;

public interface CalculatorModel extends ViewSubject {

    void setData(Queue<String> detailData);

    void accept(CalculatorMassFactory massFactory);

    void calculation();

}
