package calculators;

import model.AbstractMassCalculator;
import org.springframework.stereotype.Component;

@Component
class SheetRubberMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return DENSITY_RUBBER * fieldsValue * dataBaseValue;
    }
}