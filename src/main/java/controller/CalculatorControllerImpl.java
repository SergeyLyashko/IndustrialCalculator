package controller;

import model.CalculatorView;
import model.ViewSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.DataReceiver;
import viewcontroller.CalculatorController;

import java.sql.SQLException;
import java.util.Queue;

@Service("calculatorController")
public class CalculatorControllerImpl implements CalculatorController, ViewSubject {

    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    private CalculatorModel calculatorModel;
    private DataReceiver dataReceiver;
    private CalculatorView calculatorView;

    @Autowired
    public void setDataReceiver(DataReceiver dataReceiver){
        this.dataReceiver = dataReceiver;
    }

    @Autowired
    public void setCalculatorModel(CalculatorModel calculatorModel){
        this.calculatorModel = calculatorModel;
    }

    @Autowired
    public void setView(CalculatorView calculatorView){
        this.calculatorView = calculatorView;
    }

    @Override
    public void setCalculatorData(CalculatorData calculatorData) {
        Queue<String> data = calculatorData.getData();
        String assortment = data.poll();
        String type = data.poll();
        String number = data.poll();
        double dataBaseValue = receiveDataBaseValue(assortment, type, number);
        double[] parseData = parseData(data);
        Detail detail = calculatorModel.getDetail(dataBaseValue, parseData);
        calculatorModel.calculationMass(detail, assortment, type);
    }

    private double[] parseData(Queue<String> data) {
        DataValueParser dataValueParser = calculatorModel.getDataParser();
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
        calculatorView.resultUpdate(mass, alert);
    }

    @Override
    public void notifyMessageObservers(String message, boolean alert) {
        calculatorView.messageUpdate(message, alert);
    }
}
