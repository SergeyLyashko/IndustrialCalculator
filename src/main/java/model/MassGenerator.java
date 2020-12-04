package model;

import model.detailmass.CalculatorMassFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MassGenerator {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private final double receiveValue;
    private final boolean isArea;
    private final List<String> values;

    MassGenerator(double receiveValue, boolean isArea) {
        this.receiveValue = receiveValue;
        this.isArea = isArea;
        values = new ArrayList<>(2);
    }

    void addData(String data) {
        values.add(data);
    }

    void orderMass(AbstractMassCalculator massCalculator){
        double[] parseValues = values.stream().mapToDouble(this::parse).toArray();
        System.out.println("test parse: "+ Arrays.toString(parseValues));
        massCalculator.setDetail(new Detail(isArea, receiveValue, parseValues));
        // TODO
        double mass = massCalculator.calculationMass();
        System.out.println("test mass: "+mass);
    }

    /**
     * Получение числового значения
     * @param value Строковое представление значения
     */
    private double parse(String value) {
        if(isNotNullValue(value)){
            return Double.parseDouble(value);
        }
        // TODO написать свое исключение проверки на null для отправки сообщения об отсутствии ввода
        return 0;
    }

    private boolean isNotNullValue(String value){
        return value != null;
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
