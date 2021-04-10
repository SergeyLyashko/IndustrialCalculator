package calculators;

import model.AbstractMassCalculator;
import static java.lang.Math.PI;

class CircleSteelMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return DENSITY_STEEL * fieldsValue * (dataBaseValue * dataBaseValue) / 4 * PI;
    }
}