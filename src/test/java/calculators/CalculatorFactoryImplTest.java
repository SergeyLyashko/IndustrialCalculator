package calculators;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorFactoryImplTest {

    private CalculatorFactoryImpl calculatorFactory;

    @Before
    public void setUp() throws Exception {
        calculatorFactory = new CalculatorFactoryImpl("assortmentTest", "typeTest");
    }

    @Test
    public void createMassCalculator() {

    }
}