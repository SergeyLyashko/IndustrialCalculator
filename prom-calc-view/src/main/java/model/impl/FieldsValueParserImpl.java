package model.impl;

import controller.ViewController;
import model.FieldsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fieldsParser")
class FieldsValueParserImpl implements FieldsParser {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private static final String NOT_FULL_DATA_MESSAGE = "Введены не все параметры";
    private static final String TOO_BIG_NUM_MESSAGE = "Параметр за пределами здравого смысла";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    @Autowired
    private ViewController viewController;

    @Override
    public List<Double> parseData(Data data){
        List<Double> fieldsValue = new ArrayList<>(2);
        if(!data.isComplexArea()){
            String widthData = data.getWidthData();
            fieldsValue.add(parse(widthData));
        }
        String lengthData = data.getLengthData();
        fieldsValue.add(parse(lengthData));
        return checkedValues(fieldsValue);
    }

    private List<Double> checkedValues(List<Double> fieldsValue){
        if (fieldsValue.size() > 1) {
            if(isPossibleCalculation(fieldsValue.get(0), fieldsValue.get(1))){
                return fieldsValue;
            } else {
                return new ArrayList<>(0);
            }
        }
        return fieldsValue;
    }

    /*
     * Получение числового значения
     * @param value Строковое представление значения
     */
    private double parse(String value) {
        try {
            if (value != null && !value.isEmpty()) {
                return Double.parseDouble(value);
            }else {
                viewController.setMessage(NOT_FULL_DATA_MESSAGE, ALERT);
                viewController.setResult(ERROR, ALERT);
            }
        }catch (NumberFormatException exception){
            viewController.setMessage(TOO_BIG_NUM_MESSAGE, ALERT);
            viewController.setResult(ERROR, ALERT);
        }
        return 0;
    }

    private boolean isPossibleCalculation(double firstNum, double secondNum){
        double checkNum = MAX_NUMBER / secondNum;
        if(firstNum < checkNum){
            return true;
        }
        viewController.setMessage(TOO_BIG_NUM_MESSAGE, ALERT);
        viewController.setResult(ERROR, ALERT);
        return false;
    }
}
