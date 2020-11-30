package model;

import controller.MessageObserver;
import controller.ResultObserver;

import java.sql.SQLException;
import java.util.Queue;

public class ModelDispatcher implements CalculatorModel {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;

    private final ValueReceivable valueReceivable;
    private double value;

    public ModelDispatcher(ValueReceivable valueReceivable) {
        this.valueReceivable = valueReceivable;
    }

    @Override
    public void setDetailData(Queue<String> detailData) {
        String assortment = detailData.poll();
        String type = detailData.poll();
        String number = detailData.poll();
        try {
            this.value = valueReceivable.getValue(assortment, type, number);
        } catch (SQLException exception) {
            //TODO messages
            exception.printStackTrace();
        }
    }

    @Override
    public void setDetailValues(Queue<String> values) {
        //TODO
    }


    private boolean isValidValues(double widthNum, double lengthNum){
        if(isValidNumber(widthNum) && isValidNumber(lengthNum)){
            double checkNum = MAX_NUMBER / lengthNum;
            if(checkNum > widthNum){
                return true;
            }
            //TODO message: "ошибка! слишком большое число!";
            return false;
        }
        return false;
    }

    private boolean isValidNumber(double number){
        if(number > MAX_NUMBER){
            // TODO message "ошибка! слишком большое число!";
            return false;
        }
        if(number < 0){
            //TODO message "ошибка! отрицательное число!";
            return false;
        }
        return true;
    }
}
