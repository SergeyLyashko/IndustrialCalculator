package controller;

import database.DataBaseDispatcher;
import model.ValueReceiver;
import model.CalculatorModel;
import model.CalculatorModelImpl;
import model.detailmass.CalculatorMassFactory;
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

            CalculatorMassFactory massFactory = new CalculatorMassFactory();
            CalculatorModel model = new CalculatorModelImpl(valueReceiver);
            model.accept(massFactory);

            Controller controller = new ControllerImpl(model);

            ViewObserver observer = new View(dataBaseMenuReceiver, controller);
            model.registerObserver(observer);

        });
    }
}
