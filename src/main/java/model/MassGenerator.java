package model;

import java.util.ArrayList;
import java.util.List;

class MassGenerator {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private final double receiveValue;
    private final List<String> values;

    MassGenerator(double receiveValue) {
        this.receiveValue = receiveValue;
        values = new ArrayList<>(2);
    }

    void addData(String data) {
        values.add(data);
    }

    double generateMass(AbstractMassCalculator massCalculator){
        double[] parseValues = values.stream().mapToDouble(this::parse).toArray();
        if(parseValues.length > 0){
            //TODO Написать свое исключение если не проходит проверку validValues на переполнение
            massCalculator.setDetail(new Detail(receiveValue, parseValues));
        }
        return massCalculator.calculationMass();
    }

    /**
     * Получение числового значения
     * @param value Строковое представление значения
     */
    private double parse(String value) {
        if(value != null && !value.isEmpty()){
            return Double.parseDouble(value);
        }
        // TODO написать свое исключение проверки на null для отправки сообщения об отсутствии ввода
        return 0;
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

    // TODO ????
    private boolean isValidNumber(double number){
        if(number < 0){
            //TODO message "ошибка! отрицательное число!";
            return false;
        }
        return true;
    }
}
