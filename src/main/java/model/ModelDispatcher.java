package model;

import model.detailmass.CalculatorMassFactory;

import java.sql.SQLException;
import java.util.Queue;

public class ModelDispatcher implements CalculatorModel {

    private final ValueReceiver valueReceiver;
    private boolean isArea;
    private MassGenerator massGenerator;
    private CalculatorMassFactory massFactory;

    public ModelDispatcher(ValueReceiver valueReceiver) {
        this.valueReceiver = valueReceiver;
    }

    @Override
    public void setIsArea(boolean status) {
        this.isArea = status;
    }

    @Override
    public void accept(CalculatorMassFactory massFactory) {
        this.massFactory = massFactory;
    }

    @Override
    public void setDetailData(Queue<String> detailData) {
        String assortment = detailData.poll();
        String type = detailData.poll();
        String number = detailData.poll();
        double receiveValue = receive(assortment, type, number);
        System.out.println("test receive value: "+receiveValue);

        massGenerator = new MassGenerator(receiveValue, isArea);
        while (!detailData.isEmpty()){
            massGenerator.addData(detailData.poll());
        }
        AbstractMassCalculator massCalculator = massFactory.createMassCalculator(assortment, type);
        massGenerator.orderMass(massCalculator);
    }

    private double receive(String assortment, String type, String number){
        try {
            return valueReceiver.getValue(assortment, type, number);
        } catch (SQLException exception) {
            //TODO messages
            exception.printStackTrace();
        }
        return 0;
    }
}
