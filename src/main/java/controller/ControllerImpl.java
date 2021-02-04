package controller;

import model.CalculatorFactory;
import model.View;
import model.ViewSubject;
import view.DataReceiver;
import viewcontroller.Controller;

import java.sql.SQLException;
import java.util.Queue;

public class ControllerImpl implements Controller, ViewSubject {

    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    private final CalculatorModel model;
    private final DataReceiver dataReceiver;
    private View viewObserver;

    public ControllerImpl(CalculatorModel model, DataReceiver dataReceiver) {
        this.model = model;
        this.dataReceiver = dataReceiver;
    }

    @Override
    public void setCalculatorData(CalculatorData calculatorData) {
        Queue<String> data = calculatorData.getData();
        String assortment = data.poll();
        String type = data.poll();
        String number = data.poll();
        CalculatorFactory calculator = model.getCalculator();
        double dataBaseValue = receiveDataBaseValue(assortment, type, number);
        double[] parseData = parseData(data);
        Detail detail = model.getDetail(dataBaseValue, parseData);
        model.calculationMass(calculator, detail, assortment, type);
    }

    private double[] parseData(Queue<String> data) {
        DataValueParser dataValueParser = model.getDataParser();
        while (!data.isEmpty()){
            dataValueParser.addData(data.poll());
        }
        return dataValueParser.parseData();
    }

    private double receiveDataBaseValue(String assortment, String type, String number) {
        try {
            return dataReceiver.getValue(assortment, type, number);
        } catch (SQLException exception) {
            notifyMessageObservers(NOT_DATABASE_MESSAGE, ALERT);
            notifyResultObservers(ERROR, ALERT);
        }
        return 0;
    }

    @Override
    public void notifyResultObservers(String mass, boolean alert) {
        viewObserver.resultUpdate(mass, alert);
    }

    @Override
    public void notifyMessageObservers(String message, boolean alert) {
        viewObserver.messageUpdate(message, alert);
    }

    @Override
    public void registerObserver(View observer) {
        this.viewObserver = observer;
    }
}
