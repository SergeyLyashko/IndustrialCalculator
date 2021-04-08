package controller;

import model.ViewSubject;

public interface CalculatorModel extends ViewSubject {

    void calculationMass(Detail detail, String assortment, String type);

    Detail createDetail(double dataBaseValue, double[] fieldsValue);
}
