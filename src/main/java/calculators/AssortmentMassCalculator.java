package calculators;

import model.AbstractMassCalculator;

class AssortmentMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double fieldsValues = super.getFieldsValue();
        double dataBaseValue = super.getDataBaseValue();
        return DENSITY_STEEL * fieldsValues * dataBaseValue * 100;
    }
}