package model;

import model.AbstractMassCalculator;

public abstract class AbstractCalculatorFactory {

    AbstractMassCalculator calculatorOrder(String assortment, String type){
        return createMassCalculator(assortment, type);
    }

    protected abstract AbstractMassCalculator createMassCalculator(String assortment, String type);
}
