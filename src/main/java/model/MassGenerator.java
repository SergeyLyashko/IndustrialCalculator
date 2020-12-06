package model;

import java.util.ArrayList;
import java.util.List;

class MassGenerator {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private static final String NOT_FULL_DATA_MESSAGE = "Введены не все параметры";
    private static final String TOO_BIG_NUM_MESSAGE = "Размеры ";
    private final double receiveValue;
    private final List<String> values;
    private final CalculatorModelImpl calculatorModel;

    MassGenerator(double receiveValue, CalculatorModelImpl calculatorModel) {
        this.receiveValue = receiveValue;
        this.calculatorModel = calculatorModel;
        values = new ArrayList<>(2);
    }

    void addData(String data) {
        values.add(data);
    }

    double generateMass(AbstractMassCalculator massCalculator){
        double[] parseValues = this.values.stream().mapToDouble(this::parse).toArray();
        if(parseValues.length > 0 && isValid(parseValues)){
            massCalculator.setDetail(new Detail(receiveValue, parseValues));
        }
        return massCalculator.calculationMass();
    }

    //TODO Написать свое исключение если не проходит проверку validValues на переполнение
    private boolean isValid(double[] values){
        if(values.length == 2){
            return isNotOverflow(values[0], values[1]);
        }
        return true;
    }

    /**
     * Получение числового значения
     * @param value Строковое представление значения
     */
    private double parse(String value) {
        // TODO сделать проверку на строку по умолчанию в поле
        if(value != null && !value.isEmpty()){
            return Double.parseDouble(value);
        }else {
            calculatorModel.notifyMessageObservers(NOT_FULL_DATA_MESSAGE);
        }
        return 0;
    }

    private boolean isNotOverflow(double first, double second){
        if(isValidOneValue(first) && isValidOneValue(second)){
            double checkNum = MAX_NUMBER / second;
            if(first < checkNum){
                return true;
            }
            //TODO message: "ошибка! слишком большое число!";
            calculatorModel.notifyMessageObservers(TOO_BIG_NUM_MESSAGE);
            return false;
        }
        return false;
    }

    // TODO ????
    private boolean isValidOneValue(double value){
        if(value < 0){
            //TODO message "ошибка! отрицательное число!";
            return false;
        }
        return true;
    }
}
