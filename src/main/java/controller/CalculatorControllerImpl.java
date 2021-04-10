package controller;

import model.AbstractMassCalculator;
import model.CalculatorFactory;
import model.CalculatorView;
import model.ViewSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.DataReceiver;
import viewcontroller.CalculatorController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("calculatorController")
public class CalculatorControllerImpl implements CalculatorController, ViewSubject {

    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    private CalculatorModel calculatorModel;
    private DataReceiver dataReceiver;
    private CalculatorView calculatorView;
    private FieldsParser fieldsValue;
    private CalculatorFactory calculatorFactory;

    @Autowired
    public void setCalculatorFactory(CalculatorFactory calculatorFactory){
        this.calculatorFactory = calculatorFactory;
    }

    @Autowired
    public void setFieldsValue(FieldsParser fieldsValue){
        this.fieldsValue = fieldsValue;
    }

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
    public void calculation(CalculatorData calculatorData) {
        List<String> data = calculatorData.getFieldsData();
        Map<String, String> selectedMenuItems = calculatorData.getSelectedMenuItems();

        double dataBaseValue = receiveDataBaseValue(selectedMenuItems);
        double[] parseData = fieldsValue.parseData(data);

        AbstractMassCalculator abstractMassCalculator = getCalculator(selectedMenuItems);
        abstractMassCalculator.setDataBaseValue(dataBaseValue);
        abstractMassCalculator.setFieldsValue(parseData);

        calculatorModel.executeCalculation(abstractMassCalculator);
    }

    private AbstractMassCalculator getCalculator(Map<String, String> menuItems){
        String assortment = menuItems.get("assortment");
        String type = menuItems.get("type");
        return calculatorFactory.createMassCalculator(assortment, type);
    }

    private double receiveDataBaseValue(Map<String, String> menuItems) {
        String assortment = menuItems.get("assortment");
        String type = menuItems.get("type");
        String number = menuItems.get("number");
        try {
            return dataReceiver.receiveValue(assortment, type, number);
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
