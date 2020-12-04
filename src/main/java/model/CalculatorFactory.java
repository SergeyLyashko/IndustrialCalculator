package model;

public interface CalculatorFactory {

    AbstractMassCalculator createMassCalculator(String assortment, String type);
}
