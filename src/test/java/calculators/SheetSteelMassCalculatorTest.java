package calculators;

import model.AbstractMassCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SheetSteelMassCalculatorTest {
/*
    private Detail mockDetail;
    private Detail spyDetail;

    @Before
    public void setUp() throws Exception {
        AbstractMassCalculator mockAbstractMassCalculator = mock(AbstractMassCalculator.class);
        mockDetail = mock(Detail.class);
        mockAbstractMassCalculator.setDetail(mockDetail);

        spyDetail = spy(new Detail() {
            @Override
            public double getFieldsValue() {
                return 11.5;
            }

            @Override
            public double getDataBaseValue() {
                return 2.5;
            }
        });
    }

    @Test
    public void calculationMass() {
        double databaseValueExample = 1.1;
        double fieldValueExample = 2.2;
        when(mockDetail.getDataBaseValue()).thenReturn(databaseValueExample);
        when(mockDetail.getFieldsValue()).thenReturn(fieldValueExample);

        double dataBaseValue = spyDetail.getDataBaseValue();
        double fieldsValue = spyDetail.getFieldsValue();
        assertEquals(dataBaseValue*fieldsValue, 11.5*2.5, 0.1);
    }

 */
}