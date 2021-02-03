package calculators;

import controller.Detail;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SheetRubberMassCalculatorTest {

    private Detail detail;
    @Before
    public void setUp() throws Exception {
        detail = new Detail(){
            @Override
            public double getFieldsValue() {
                return 10;
            }

            @Override
            public double getDataBaseValue() {
                return 10;
            }
        };
    }

    @Test
    public void calculationMass() {
        double dataBaseValue = detail.getDataBaseValue();
        double fieldsValue = detail.getFieldsValue();
        assertEquals(dataBaseValue*fieldsValue, 10.0*10.0, 0.1);
    }
}