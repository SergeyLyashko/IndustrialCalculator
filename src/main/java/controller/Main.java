package controller;

import database.DataBaseDispatcher;
import model.CalculatorFactory;
import model.ValueReceiver;
import detailmass.CalculatorModel;
import model.CalculatorModelImpl;
import detailmass.CalculatorMassFactory;
import view.Controller;
import view.View;
import view.DataBaseMenuReceiver;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            DataBaseDispatcher dataBaseDispatcher = new DataBaseDispatcher();
            // TODO заменить на единый интерфейс ???
            DataBaseMenuReceiver dataBaseMenuReceiver = dataBaseDispatcher.getMenuReceiver();
            ValueReceiver valueReceiver = dataBaseDispatcher.getValueReceiver();

            CalculatorFactory massFactory = new CalculatorMassFactory();
            CalculatorModel model = new CalculatorModelImpl(valueReceiver, massFactory);

            Controller controller = new ControllerImpl(model);

            ViewObserver observer = new View(dataBaseMenuReceiver, controller);
            model.registerObserver(observer);

        });
    }
}
