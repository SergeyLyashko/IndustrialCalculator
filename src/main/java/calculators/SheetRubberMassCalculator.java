package calculators;

import model.AbstractMassCalculator;

class SheetRubberMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return DENSITY_RUBBER * fieldsValue * dataBaseValue;
    }
}