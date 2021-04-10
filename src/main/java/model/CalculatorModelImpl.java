package model;

import controller.CalculatorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

@Service("calculatorModel")
public class CalculatorModelImpl implements CalculatorModel {

    private static final String RESULT_MESSAGE = "Результат скопирован в буфер обмена";
    private static final boolean CALM = false;
    private DecimalFormat decimalFormat;
    private CalculatorView calculatorView;

    @Autowired
    public void setView(CalculatorView calculatorView){
        this.calculatorView = calculatorView;
    }

    @Autowired
    public void setDecimalFormat(DecimalFormat decimalFormat){
        this.decimalFormat = decimalFormat;
    }

    @Override
    public void executeCalculation(AbstractMassCalculator abstractMassCalculator) {
        double mass = abstractMassCalculator.calculation();
        if(mass > 0) {
            String formattedResult = decimalFormat.format(mass);
            notifyObservers(formattedResult);
        }
    }

    private void notifyObservers(String result){
        calculatorView.resultUpdate(result, CALM);
        calculatorView.messageUpdate(RESULT_MESSAGE, CALM);
        setResultToSystemClipboard(result);
    }

    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(value), null);
    }
}
