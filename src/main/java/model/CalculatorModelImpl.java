package model;

import controller.CalculatorModel;
import controller.Detail;
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
    private CalculatorFactory calculatorFactory;

    @Autowired
    public void setCalculatorFactory(CalculatorFactory calculatorFactory){
        this.calculatorFactory = calculatorFactory;
    }

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
    public void calculationMass(Detail detail, String assortment, String type) {
        AbstractMassCalculator massCalculator = calculatorFactory.createMassCalculator(assortment, type);
        massCalculator.setDetail(detail);
        double mass = massCalculator.calculationMass();
        if(mass > 0) {
            notifyResult(mass);
        }
    }

    @Override
    public Detail createDetail(double dataBaseValue, double[] fieldsValue) {
        return new DetailImpl(dataBaseValue, fieldsValue);
    }

    private void notifyResult(double mass){
        String formattedResult = decimalFormat.format(mass);
        setResultToSystemClipboard(formattedResult);
        notifyResultObservers(formattedResult, CALM);
        notifyMessageObservers(RESULT_MESSAGE, CALM);
    }

    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(value), null);
    }
}
