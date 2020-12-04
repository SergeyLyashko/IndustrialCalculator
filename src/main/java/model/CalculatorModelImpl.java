package model;

import model.detailmass.CalculatorMassFactory;

import java.sql.SQLException;
import java.util.Queue;

public class CalculatorModelImpl implements CalculatorModel {

    private final ValueReceiver valueReceiver;
    private CalculatorMassFactory calculatorMassFactory;

    public CalculatorModelImpl(ValueReceiver valueReceiver) {
        this.valueReceiver = valueReceiver;
    }

    @Override
    public void accept(CalculatorMassFactory massFactory) {
        this.calculatorMassFactory = massFactory;
    }

    @Override
    public void setDetailData(Queue<String> detailData) {
        AbstractMassCalculator massCalculator = createCalculator(detailData);
        double dataBaseValue = getDataBaseValue(detailData);
        // TODO написать свое исключение вместо проверки на пустую очередь
        if(!detailData.isEmpty()){
            MassGenerator massGenerator = createGenerator(dataBaseValue, detailData);
            massGenerator.orderMass(massCalculator);
        }
    }

    private double getDataBaseValue(Queue<String> detailData){
        if(detailData.size() > 2){
            String assortment = detailData.poll();
            String type = detailData.poll();
            String number = detailData.poll();
            return receiveValue(assortment, type, number);
        }
        return 0;
    }

    private double receiveValue(String assortment, String type, String number){
        try {
            return valueReceiver.getValue(assortment, type, number);
        } catch (SQLException exception) {
            //TODO messages
            exception.printStackTrace();
        }
        return 0;
    }

    private AbstractMassCalculator createCalculator(Queue<String> detailData){
        if(detailData.size() > 2){
            String assortment = detailData.peek();
            String type = detailData.peek();
            return calculatorMassFactory.createMassCalculator(assortment, type);
        }
        // TODO Написать исключение
        return null;
    }

    private MassGenerator createGenerator(double receiveValue, Queue<String> detailData){
        MassGenerator massGenerator = new MassGenerator(receiveValue);
        while (!detailData.isEmpty()){
            massGenerator.addData(detailData.poll());
        }
        return massGenerator;
    }
}
