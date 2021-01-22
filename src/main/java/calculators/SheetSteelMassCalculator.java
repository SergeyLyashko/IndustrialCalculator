package calculators;

import model.AbstractMassCalculator;

class SheetSteelMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculationMass() {
        double fieldsValue = super.getFieldsValue();
        double dataBaseValue = super.getDataBaseValue();
        return DENSITY_STEEL * fieldsValue * dataBaseValue;
    }
}