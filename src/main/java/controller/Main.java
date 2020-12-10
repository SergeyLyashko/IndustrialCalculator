package controller;

import database.DataBaseDispatcher;
import model.CalculatorFactory;
import model.ValueReceiver;
import model.CalculatorModelImpl;
import detailmass.CalculatorFactoryImpl;
import view.Controller;
import view.View;
import view.DataBaseMenuReceiver;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            // TODO заменить на единый интерфейс ???
            DataBaseDispatcher dataBaseDispatcher = new DataBaseDispatcher();
            DataBaseMenuReceiver dataBaseMenuReceiver = dataBaseDispatcher.getMenuReceiver();
            ValueReceiver valueReceiver = dataBaseDispatcher.getValueReceiver();

            CalculatorFactory calculatorFactory = new CalculatorFactoryImpl();
            CalculatorModel model = new CalculatorModelImpl(valueReceiver, calculatorFactory);

            Controller controller = new ControllerImpl(model);

            ViewObserver observer = new View(dataBaseMenuReceiver, controller);
            model.registerObserver(observer);

        });
    }
}
