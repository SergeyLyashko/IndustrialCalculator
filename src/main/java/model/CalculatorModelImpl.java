package model;

import controller.ViewObserver;
import controller.ViewSubject;
import detailmass.CalculatorModel;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Queue;

public class CalculatorModelImpl implements CalculatorModel, ViewSubject {

    private static final String RESULT_MESSAGE = "Результат скопирован в буфер обмена";
    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";

    private final ValueReceiver valueReceiver;
    private final CalculatorFactory massFactory;
    private ViewObserver observer;
    private Queue<String> detailData;
    private static final boolean ALERT = true;
    private static final boolean CALM = false;

    public CalculatorModelImpl(ValueReceiver valueReceiver, CalculatorFactory massFactory) {
        this.valueReceiver = valueReceiver;
        this.massFactory = massFactory;
    }

    @Override
    public void notifyResultObservers(String mass, boolean alert) {
        observer.resultUpdate(mass, alert);
    }

    @Override
    public void notifyMessageObservers(String message, boolean alert) {
        observer.messageUpdate(message, alert);
    }

    @Override
    public void registerObserver(ViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void setData(Queue<String> detailData) {
        this.detailData = detailData;
    }

    @Override
    public void calculation() {
        String assortment = detailData.poll();
        String type = detailData.poll();
        String number = detailData.poll();
        AbstractMassCalculator massCalculator = massFactory.createMassCalculator(assortment, type);
        double value = receiveValue(assortment, type, number);
        MassGenerator generator = createGenerator(value);
        double mass = generator.generateMass(massCalculator);
        if(mass > 0) {
            notifyResult(mass);
        }
    }

    private double receiveValue(String assortment, String type, String number){
        try {
            return valueReceiver.getValue(assortment, type, number);
        } catch (SQLException exception) {
            notifyMessageObservers(NOT_DATABASE_MESSAGE, ALERT);
            notifyResultObservers(ERROR, ALERT);
        }
        return 0;
    }

    private MassGenerator createGenerator(double receiveValue){
        MassGenerator massGenerator = new MassGenerator(receiveValue, this);
        while (!detailData.isEmpty()){
            massGenerator.addData(detailData.poll());
        }
        return massGenerator;
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
