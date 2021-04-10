package calculators;

import model.AbstractMassCalculator;
import model.CalculatorFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorFactoryImplTest {
/*
    private CalculatorFactory calculatorFactory;
    private Detail detail;

    @Before
    public void setUp() throws Exception {
        detail = new Detail() {
            @Override
            public double getFieldsValue() {
                return 10;
            }

            @Override
            public double getDataBaseValue() {
                return 20;
            }
        };
        calculatorFactory = new CalculatorFactoryImpl();
    }

    @Test
    public void createMassCalculator() {
        AbstractMassCalculator abstractMassCalculator = calculatorFactory.createMassCalculator("Лист", "тонколистовая");
        abstractMassCalculator.setDetail(detail);
        double expected = ((SheetSteelMassCalculator) abstractMassCalculator).calculationMass();

        SheetSteelMassCalculator sheetSteelMassCalculator = new SheetSteelMassCalculator();
        sheetSteelMassCalculator.setDetail(detail);
        double actual = sheetSteelMassCalculator.calculationMass();

        assertEquals(expected, actual, 0.1);
    }

 */
}