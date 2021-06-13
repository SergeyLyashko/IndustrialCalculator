package controller.impl;

import controller.CalculatorController;
import controller.ViewController;
import model.AbstractMassCalculator;
import model.FieldsParser;
import model.impl.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import database.MenuListProducer;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service("calculatorController")
class CalculatorControllerImpl implements CalculatorController, ApplicationContextAware {

    private static final String RESULT_MESSAGE = "Результат скопирован в буфер обмена";
    private static final boolean CALM = false;
    @Autowired
    private MenuListProducer menuListProducer;
    @Autowired
    private FieldsParser fieldsParser;
    @Autowired
    private ViewController controller;
    @Autowired
    private DecimalFormat decimalFormat;
    @Autowired
    private Data data;
    private ApplicationContext applicationContext;
    private final StringBuilder beanNameBuilder;

    CalculatorControllerImpl() {
        beanNameBuilder = new StringBuilder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void calculation() throws SQLException {
        List<Double> parseData = fieldsParser.parseData(data);
        System.out.println(data.toString());
        double dataBaseValue = receiveDataBaseValue();

        AbstractMassCalculator abstractMassCalculator = getCalculator();
        abstractMassCalculator.setDataBaseValue(dataBaseValue);
        abstractMassCalculator.setFieldsValue(parseData);

        executeCalculation(abstractMassCalculator);
    }

    private void executeCalculation(AbstractMassCalculator abstractMassCalculator) {
        double mass = abstractMassCalculator.calculation();
        if(mass > 0) {
            String formattedResult = decimalFormat.format(mass);
            notifyObservers(formattedResult);
        }
    }

    private void notifyObservers(String result){
        controller.setResult(result, CALM);
        controller.setMessage(RESULT_MESSAGE, CALM);
        setResultToSystemClipboard(result);
    }

    // метод копирования в буфер обмена при выводе результата
    private void setResultToSystemClipboard(String value){
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(value), null);
    }

    private AbstractMassCalculator getCalculator(){
        beanNameBuilder.delete(0, beanNameBuilder.length());
        String assortment = data.getAssortment();
        String type = data.getType();
        String bean = beanNameBuilder.append(assortment)
                .append(" ")
                .append(type)
                .toString();
        return applicationContext.getBean(bean, AbstractMassCalculator.class);
    }

    private double receiveDataBaseValue() throws SQLException {
        String assortment = data.getAssortment();
        String type = data.getType();
        String number = data.getNumber();
        return menuListProducer.produceMenuItemsValue(assortment, type, number);
    }
}
