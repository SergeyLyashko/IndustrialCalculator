package controller;

import model.AbstractMassCalculator;
import model.CalculatorModel;
import model.FieldsParser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import database.DataReceiver;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("calculatorController")
class CalculatorControllerImpl implements CalculatorController, ApplicationContextAware {

    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    private static final String ASSORTMENT = "assortment";
    private static final String TYPE = "type";
    private static final String NUMBER = "number";

    @Autowired
    private CalculatorModel calculatorModel;
    @Autowired
    private DataReceiver dataReceiver;
    @Autowired
    private FieldsParser fieldsParser;
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
    public void calculation(CalculatorData calculatorData) {
        List<String> data = calculatorData.getFieldsData();
        Map<String, String> selectedMenuItems = calculatorData.getSelectedMenuItems();

        double dataBaseValue = receiveDataBaseValue(selectedMenuItems);
        double[] parseData = fieldsParser.parseData(data);

        AbstractMassCalculator abstractMassCalculator = getCalculator(selectedMenuItems);
        abstractMassCalculator.setDataBaseValue(dataBaseValue);
        abstractMassCalculator.setFieldsValue(parseData);

        calculatorModel.executeCalculation(abstractMassCalculator);
    }

    private AbstractMassCalculator getCalculator(Map<String, String> menuItems){
        beanNameBuilder.delete(0, beanNameBuilder.length());
        String assortment = menuItems.get(ASSORTMENT);
        String type = menuItems.get(TYPE);
        String bean = beanNameBuilder.append(assortment)
                .append(" ")
                .append(type)
                .toString();
        return applicationContext.getBean(bean, AbstractMassCalculator.class);
    }

    private double receiveDataBaseValue(Map<String, String> menuItems) {
        String assortment = menuItems.get(ASSORTMENT);
        String type = menuItems.get(TYPE);
        String number = menuItems.get(NUMBER);
        try {
            return dataReceiver.receiveValue(assortment, type, number);
        } catch (SQLException exception) {
            //viewController.setMessage(NOT_DATABASE_MESSAGE, ALERT);
            //viewController.setResult(ERROR, ALERT);
        }
        return 0;
    }
}
