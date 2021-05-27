package model;

import java.util.Arrays;

public abstract class AbstractMassCalculator {

    // Плотность стали марки Ст3 7,85e-6 кг/мм3 = 7850 кг/м3
    protected static final double DENSITY_STEEL = 7.85e-6;
    // Плотность резины ГОСТ 7338-90 лист ТМКЩ 1.25e-7 кг/мм3 = 125 кг/м3
    protected static final double DENSITY_RUBBER = 1.25e-6;

    private double dataBaseValue;
    private double reduceFieldsValue;

    protected double getFieldsValue(){
        return reduceFieldsValue;
    }

    protected double getDataBaseValue(){
        return dataBaseValue;
    }

    public void setDataBaseValue(double dataBaseValue) {
        this.dataBaseValue = dataBaseValue;
    }

    public void setFieldsValue(double[] values) {
        this.reduceFieldsValue = Arrays.stream(values).reduce(1, (acc, element) -> acc * element);
    }

    protected abstract double calculation();
}