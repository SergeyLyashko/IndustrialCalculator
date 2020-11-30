package model;

public abstract class AbstractMassCalculator {

    // Плотность стали марки Ст3 7,85e-6 кг/мм3 = 7850 кг/м3
    protected static final double DENSITY_STEEL = 7.85e-6;
    // Плотность резины ГОСТ 7338-90 лист ТМКЩ 1.25e-7 кг/мм3 = 125 кг/м3
    protected static final double DENSITY_RUBBER = 1.25e-6;

    private Detail detail;

    void setDetail(Detail detail) {
        this.detail = detail;
    }

    protected double getDataBaseValue(){
        return detail.getDataBaseValue();
    }

    protected double getDetailLength(){
        return detail.getLength();
    }

    protected double getDetailWidth(){
        return detail.getWidth();
    }

    protected abstract double calculationMass();
}