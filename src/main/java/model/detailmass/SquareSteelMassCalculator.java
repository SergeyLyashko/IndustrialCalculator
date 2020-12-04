package model.detailmass;

import model.AbstractMassCalculator;

class SquareSteelMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculationMass() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return DENSITY_STEEL * fieldsValue * (dataBaseValue * dataBaseValue);
    }
}
