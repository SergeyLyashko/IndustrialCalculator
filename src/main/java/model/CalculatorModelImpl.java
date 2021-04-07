package model;

import controller.CalculatorModel;
import controller.DataValueParser;
import controller.Detail;
import calculators.CalculatorFactoryImpl;
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
    public void calculationMass(CalculatorFactory calculator, Detail detail, String assortment, String type) {
        AbstractMassCalculator massCalculator = calculator.createMassCalculator(assortment, type);
        massCalculator.setDetail(detail);
        double mass = massCalculator.calculationMass();
        if(mass > 0) {
            notifyResult(mass);
        }
    }

    @Override
    public CalculatorFactory getCalculator() {
        return new CalculatorFactoryImpl();
    }

    @Override
    public DataValueParser getDataParser() {
        return new DataValueParserImpl(this);
    }

    @Override
    public Detail getDetail(double dataBaseValue, double[] parseData) {
        return new DetailImpl(dataBaseValue, parseData);
    }

    private void notifyResult(double mass){
        String formattedResult = formatDoubleToString(mass);
        setResultToSystemClipboard(formattedResult);
        notifyResultObservers(formattedResult, CALM);
        notifyMessageObservers(RESULT_MESSAGE, CALM);
    }

    //форматирование строки результата
    private String formatDoubleToString(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return decimalFormat.format(value);
    }

    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
    }
}
