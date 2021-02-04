package controller;

import model.CalculatorFactory;
import model.ViewSubject;

public interface CalculatorModel extends ViewSubject {

    void calculationMass(CalculatorFactory calculator, Detail detail, String assortment, String type);

    CalculatorFactory getCalculator();

    DataValueParser getDataParser();

    Detail getDetail(double dataBaseValue, double[] parseData);
}
