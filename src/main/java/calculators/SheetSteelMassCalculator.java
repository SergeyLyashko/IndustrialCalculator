package calculators;

import model.AbstractMassCalculator;
import org.springframework.stereotype.Component;

@Component
class SheetSteelMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double fieldsValue = super.getFieldsValue();
        double dataBaseValue = super.getDataBaseValue();
        return DENSITY_STEEL * fieldsValue * dataBaseValue;
    }
}