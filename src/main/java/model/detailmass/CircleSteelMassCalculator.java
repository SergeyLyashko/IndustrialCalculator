package model.detailmass;

import model.AbstractMassCalculator;
import static java.lang.Math.PI;

class CircleSteelMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculationMass() {
        return DENSITY_STEEL * super.getDetailLength() *
                (super.getDataBaseValue() * super.getDataBaseValue()) / 4 * PI;
    }
}