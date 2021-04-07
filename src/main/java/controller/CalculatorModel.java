package controller;

import model.ViewSubject;

public interface CalculatorModel extends ViewSubject {

    void calculationMass(Detail detail, String assortment, String type);

    //DataValueParser getDataParser();

    Detail createDetail(double dataBaseValue, double[] fieldsValue);
}
