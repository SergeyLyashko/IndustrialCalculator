package controller;

import model.AbstractMassCalculator;
import model.ViewSubject;

public interface CalculatorModel extends ViewSubject {

    void executeCalculation(AbstractMassCalculator abstractMassCalculator);
}
