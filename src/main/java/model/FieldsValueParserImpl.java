package model;

import controller.FieldsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fieldsParser")
class FieldsValueParserImpl implements FieldsParser {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private static final String NOT_FULL_DATA_MESSAGE = "Введены не все параметры";
    private static final String TOO_BIG_NUM_MESSAGE = "Размеры за пределами здравого смысла";
    private static final String NEGATIVE_MESSAGE = "Отрицательное значение размера";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    private CalculatorView calculatorView;

    @Autowired
    public void setView(CalculatorView calculatorView){
        this.calculatorView = calculatorView;
    }

    @Override
    public double[] parseData(List<String> data){
        double[] parseValues = data.stream().mapToDouble(this::parse).toArray();
        if(parseValues.length > 0 && isValid(parseValues)){
            return parseValues;
        }
        return null;
    }

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
        if(value != null && !value.isEmpty()){
            return Double.parseDouble(value);
        }else {
            calculatorView.messageUpdate(NOT_FULL_DATA_MESSAGE, ALERT);
            calculatorView.resultUpdate(ERROR, ALERT);
        }
        return 0;
    }

    private boolean isNotOverflow(double first, double second){
        if(isValidOneValue(first) && isValidOneValue(second)){
            double checkNum = MAX_NUMBER / second;
            if(first < checkNum){
                return true;
            }
            calculatorView.messageUpdate(TOO_BIG_NUM_MESSAGE, ALERT);
            calculatorView.resultUpdate(ERROR, ALERT);
            return false;
        }
        return false;
    }

    private boolean isValidOneValue(double value){
        if(value < 0){
            calculatorView.messageUpdate(NEGATIVE_MESSAGE, ALERT);
            calculatorView.resultUpdate(ERROR, ALERT);
            return false;
        }
        return true;
    }
}
