package calculators;

import model.AbstractMassCalculator;
import org.springframework.stereotype.Component;

import static java.lang.Math.PI;

@Component
class CircleSteelMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return DENSITY_STEEL * fieldsValue * (dataBaseValue * dataBaseValue) / 4 * PI;
    }
}