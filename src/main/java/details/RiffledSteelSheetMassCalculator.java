package details;

import model.AbstractMassCalculator;

class RiffledSteelSheetMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculationMass() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return fieldsValue / 1000000 * dataBaseValue;
    }
}
