package model.detailmass;

import model.AbstractMassCalculator;

class SheetRubberMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculationMass() {
        return DENSITY_RUBBER * super.getDetailLength() * super.getDetailWidth() * super.getDataBaseValue();
    }
}