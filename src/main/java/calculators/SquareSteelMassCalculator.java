package calculators;

import model.AbstractMassCalculator;
import org.springframework.stereotype.Component;

@Component
public class SquareSteelMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return DENSITY_STEEL * fieldsValue * (dataBaseValue * dataBaseValue);
    }
}
