package controller;

import model.ViewSubject;

public interface CalculatorModel extends ViewSubject {

    void calculationMass(Detail detail, String assortment, String type);

    DataValueParser getDataParser();

    Detail getDetail(double dataBaseValue, double[] parseData);
}
