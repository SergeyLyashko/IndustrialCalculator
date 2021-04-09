package controller;

import model.AbstractMassCalculator;
import model.ViewSubject;

public interface CalculatorModel extends ViewSubject {

    Detail createDetail(double dataBaseValue, double[] fieldsValue);

    void calculationMass(Detail detail, AbstractMassCalculator abstractMassCalculator);
}
