package controller;

import model.CalculatorFactory;
import model.ViewSubject;

public interface CalculatorModel extends ViewSubject {

    void calculationMass(CalculatorFactory calculator, Detail detail);

    CalculatorFactory getCalculator(String assortment, String type);

    DataValueParser getDataParser();

    Detail getDetail(double dataBaseValue, double[] parseData);
}
