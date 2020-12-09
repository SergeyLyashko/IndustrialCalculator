package model;

import controller.ViewObserver;
import model.detailmass.CalculatorMassFactory;

import java.sql.SQLException;
import java.util.Queue;

public class CalculatorModelImpl implements CalculatorModel, ViewSubject {

    private static final String RESULT_MESSAGE = "Результат скопирован в буфер обмена";
    private static final String NOT_FULL_DATA_MESSAGE = "Введены не все параметры";
    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private final ValueReceiver valueReceiver;
    private CalculatorMassFactory massFactory;
    private ViewObserver observer;
    private Queue<String> detailData;

    public CalculatorModelImpl(ValueReceiver valueReceiver) {
        this.valueReceiver = valueReceiver;
    }

    @Override
    public void accept(CalculatorMassFactory massFactory) {
        this.massFactory = massFactory;
    }

    @Override
    public void notifyResultObservers(double mass) {
        observer.resultUpdate(mass);
    }

    @Override
    public void notifyMessageObservers(String message) {
        observer.messageUpdate(message);
    }

    @Override
    public void registerObserver(ViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void setData(Queue<String> detailData) {
        this.detailData = detailData;
    }

    @Override
    public void calculation() {
        if(detailData.size() > 3){
            String assortment = detailData.poll();
            String type = detailData.poll();
            String number = detailData.poll();
            AbstractMassCalculator massCalculator = massFactory.createMassCalculator(assortment, type);
            double value = receiveValue(assortment, type, number);
            MassGenerator generator = createGenerator(value);
            double mass = generator.generateMass(massCalculator);
            if(mass > 0) {
                notifyResultObservers(mass);
                notifyMessageObservers(RESULT_MESSAGE);
            }
        }else{
            notifyMessageObservers(NOT_FULL_DATA_MESSAGE);
        }
    }

    private double receiveValue(String assortment, String type, String number){
        try {
            return valueReceiver.getValue(assortment, type, number);
        } catch (SQLException exception) {
            notifyMessageObservers(NOT_DATABASE_MESSAGE);
        }
        return 0;
    }

    private MassGenerator createGenerator(double receiveValue){
        MassGenerator massGenerator = new MassGenerator(receiveValue, this);
        while (!detailData.isEmpty()){
            massGenerator.addData(detailData.poll());
        }
        return massGenerator;
    }
}
