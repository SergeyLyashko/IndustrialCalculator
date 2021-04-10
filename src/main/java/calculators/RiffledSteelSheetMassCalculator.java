package calculators;

import model.AbstractMassCalculator;
import org.springframework.stereotype.Component;

@Component
public class RiffledSteelSheetMassCalculator extends AbstractMassCalculator {

    @Override
    public double calculation() {
        double dataBaseValue = super.getDataBaseValue();
        double fieldsValue = super.getFieldsValue();
        return fieldsValue / 1000000 * dataBaseValue;
    }
}
