package model;

import controller.ViewObserver;
import model.detailmass.CalculatorMassFactory;

import java.sql.SQLException;
import java.util.Queue;

public class CalculatorModelImpl implements CalculatorModel, ViewSubject {

    private static final String RESULT_MESSAGE = "Результат скопирован в буфер обмена";
    private final ValueReceiver valueReceiver;
    private CalculatorMassFactory massFactory;
    private ViewObserver observer;
    private double mass;
    private Queue<String> detailData;

    public CalculatorModelImpl(ValueReceiver valueReceiver) {
        this.valueReceiver = valueReceiver;
    }

    @Override
    public void accept(CalculatorMassFactory massFactory) {
        this.massFactory = massFactory;
    }

    @Override
    public void notifyObservers() {
        observer.resultUpdate(mass);
        // TODO ???
        observer.messageUpdate(RESULT_MESSAGE);
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
            this.mass = generator.generateMass(massCalculator);
            notifyObservers();
        }
        // TODO Написать исключение на неполную дату
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

    private MassGenerator createGenerator(double receiveValue){
        MassGenerator massGenerator = new MassGenerator(receiveValue);
        while (!detailData.isEmpty()){
            massGenerator.addData(detailData.poll());
        }
        return massGenerator;
    }
}
