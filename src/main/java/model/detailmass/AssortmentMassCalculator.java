package model.detailmass;

import model.AbstractMassCalculator;

class AssortmentMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculationMass() {
        double area = super.getDataBaseValue();
        double length = super.getDetailLength();
        return DENSITY_STEEL * area * length * 100;
    }
}