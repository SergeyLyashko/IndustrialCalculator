package model;

import controller.CalculatorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

@Service("calculatorModel")
public class CalculatorModelImpl implements CalculatorModel, ViewSubject {

    private static final String RESULT_MESSAGE = "Результат скопирован в буфер обмена";
    private static final boolean CALM = false;
    private CalculatorView calculatorView;
    private DecimalFormat decimalFormat;

    @Autowired
    public void setDecimalFormat(DecimalFormat decimalFormat){
        this.decimalFormat = decimalFormat;
    }

    @Autowired
    public void setView(CalculatorView calculatorView){
        this.calculatorView = calculatorView;
    }

    @Override
    public void notifyResultObservers(String mass, boolean alert) {
        calculatorView.resultUpdate(mass, alert);
    }

    @Override
    public void notifyMessageObservers(String message, boolean alert) {
        calculatorView.messageUpdate(message, alert);
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
        notifyResultObservers(result, CALM);
        notifyMessageObservers(RESULT_MESSAGE, CALM);
        setResultToSystemClipboard(result);
    }

    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(value), null);
    }
}
