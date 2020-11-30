package model.detailmass;

import model.AbstractMassCalculator;

class RiffledSteelSheetMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculationMass() {
        return super.getDetailLength() * super.getDetailWidth() / 1000000 * super.getDataBaseValue();
    }
}