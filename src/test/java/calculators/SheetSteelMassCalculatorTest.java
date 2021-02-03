package calculators;

import controller.Detail;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SheetSteelMassCalculatorTest {

    private Detail detail;
    @Before
    public void setUp() throws Exception {
        detail = new Detail(){
            @Override
            public double getFieldsValue() {
                return 11.5;
            }

            @Override
            public double getDataBaseValue() {
                return 2.5;
            }
        };
    }

    @Test
    public void calculationMass() {
        double dataBaseValue = detail.getDataBaseValue();
        double fieldsValue = detail.getFieldsValue();
        assertEquals(dataBaseValue*fieldsValue, 11.5*2.5, 0.1);
    }
}