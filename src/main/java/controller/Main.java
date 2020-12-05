package controller;

import database.DataBaseDispatcher;
import model.ValueReceiver;
import model.CalculatorModel;
import model.CalculatorModelImpl;
import model.detailmass.CalculatorMassFactory;
import view.ViewDispatcher;
import view.MenuListReceiver;

import java.awt.*;

public class Main {
    public static void main(String[] args){

        EventQueue.invokeLater(() -> {

            DataBaseDispatcher dataBaseDispatcher = new DataBaseDispatcher();
            MenuListReceiver menuListReceiver = dataBaseDispatcher.getMenuReceiver();
            ValueReceiver valueReceiver = dataBaseDispatcher.getValueReceiver();

            CalculatorMassFactory massFactory = new CalculatorMassFactory();
            CalculatorModel model = new CalculatorModelImpl(valueReceiver);
            model.accept(massFactory);

            Controller controller = new ControllerImpl(model);

            ViewObserver observer = new ViewDispatcher(menuListReceiver, model, controller);
            model.registerObserver(observer);

        });
    }
}
