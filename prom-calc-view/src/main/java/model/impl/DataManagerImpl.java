package model.impl;

import controller.ViewController;
import model.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dataManager")
class DataManagerImpl implements DataManager {

    // максимально возможное значение введенного или вычисляемого числа
    private static final double MAX_NUMBER = Double.MAX_VALUE;
    private static final String NOT_FULL_DATA_MESSAGE = "Введены не все параметры";
    private static final String TOO_BIG_NUM_MESSAGE = "Параметр за пределами здравого смысла";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;
    @Autowired
    private ViewController viewController;
    @Autowired
    private Data data;

    @Override
    public List<Double> parseData(){
        List<Double> fieldsValue = new ArrayList<>(2);
        if(!data.isComplexArea() && data.isDetailHaveWidth()){
            String widthData = data.getWidthData();
            fieldsValue.add(parse(widthData));
        }
        String lengthData = data.getLengthData();
        fieldsValue.add(parse(lengthData));
        return checkedValues(fieldsValue);
    }

    @Override
    public void checkDataWidth(){
        String detailType = data.getType();
        data.setDetailHaveWidth(
                detailType.equalsIgnoreCase("резиновая пластина") || detailType.equalsIgnoreCase("тонколистовая") ||
                detailType.equalsIgnoreCase("толстолистовая") || detailType.equalsIgnoreCase("рифленая(ромб)")
        );
    }

    @Override
    public boolean isAreaOn() {
        return data.isComplexArea();
    }

    @Override
    public boolean haveWidth() {
        return data.isDetailHaveWidth();
    }

    @Override
    public String getDataAssortment() {
        return data.getAssortment();
    }

    @Override
    public String getDataType() {
        return data.getType();
    }

    @Override
    public String getDataNumber() {
        return data.getNumber();
    }

    @Override
    public void setIsComplexArea(boolean status) {
        data.setComplexArea(status);
    }

    @Override
    public void setWidthData(String textValue) {
        data.setWidthData(textValue);
    }

    @Override
    public void setLengthData(String textValue) {
        data.setLengthData(textValue);
    }

    @Override
    public void setNumber(String selectedNumber) {
        data.setNumber(selectedNumber);
    }

    @Override
    public void setAssortment(String selectedAssortment) {
        data.setAssortment(selectedAssortment);
    }

    @Override
    public void setType(String selectedType) {
        data.setType(selectedType);
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
