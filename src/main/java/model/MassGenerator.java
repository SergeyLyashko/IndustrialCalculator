package model;

import controller.ResultObserver;

import java.util.ArrayList;
import java.util.List;

class MassGenerator implements ResultSubject {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private final double receiveValue;
    private final List<String> values;
    private ResultObserver observer;

    MassGenerator(double receiveValue) {
        this.receiveValue = receiveValue;
        values = new ArrayList<>(2);
    }

    void addData(String data) {
        values.add(data);
    }

    void orderMass(AbstractMassCalculator massCalculator){
        double[] parseValues = values.stream().mapToDouble(this::parse).toArray();
        if(parseValues.length > 1){
            //TODO Написать свое исключение если не проходит проверку validValues
        }
        massCalculator.setDetail(new Detail(receiveValue, parseValues));
        // TODO наблюдатель
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

    // TODO ????
    private boolean isValidNumber(double number){
        if(number < 0){
            //TODO message "ошибка! отрицательное число!";
            return false;
        }
        return true;
    }

    @Override
    public void notifyObservers() {
        observer.resultUpdate();
    }

    @Override
    public void registerObserver(ResultObserver observer) {
        this.observer = observer;
    }
}
