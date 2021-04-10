package controller;

import model.AbstractMassCalculator;
import model.CalculatorView;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import view.DataReceiver;
import viewcontroller.CalculatorController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("calculatorController")
public class CalculatorControllerImpl implements CalculatorController, ApplicationContextAware {

    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    private CalculatorModel calculatorModel;
    private DataReceiver dataReceiver;
    private CalculatorView calculatorView;
    private FieldsParser fieldsValue;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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

    // TODO optimize it !
    private AbstractMassCalculator getCalculator(Map<String, String> menuItems){
        String assortment = menuItems.get("assortment");
        String type = menuItems.get("type");
        String bean = assortment+" "+type;
        return applicationContext.getBean(bean, AbstractMassCalculator.class);
    }

    private double receiveDataBaseValue(Map<String, String> menuItems) {
        String assortment = menuItems.get("assortment");
        String type = menuItems.get("type");
        String number = menuItems.get("number");
        try {
            return dataReceiver.receiveValue(assortment, type, number);
        } catch (SQLException exception) {
            calculatorView.messageUpdate(NOT_DATABASE_MESSAGE, ALERT);
            calculatorView.resultUpdate(ERROR, ALERT);

        }
        return 0;
    }
}
