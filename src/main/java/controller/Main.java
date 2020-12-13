package controller;

import database.DataBaseDispatcher;
import model.CalculatorFactory;
import model.ValueReceiver;
import model.CalculatorModelImpl;
import detailmass.CalculatorFactoryImpl;
import model.ViewObserver;
import viewcontroller.Controller;
import view.DataBaseMenuReceiver;
import view.ViewController;
import view.ViewDispatcherDI;
import viewcontroller.ViewControllerImpl;
import viewcontroller.ViewModel;
import viewmodel.ViewModelImpl;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            DataBaseDispatcher dataBaseDispatcher = new DataBaseDispatcher();
            DataBaseMenuReceiver dataBaseMenuReceiver = dataBaseDispatcher.getMenuReceiver();
            ValueReceiver valueReceiver = dataBaseDispatcher.getValueReceiver();

            CalculatorFactory calculatorFactory = new CalculatorFactoryImpl();
            CalculatorModel model = new CalculatorModelImpl(valueReceiver, calculatorFactory);

            Controller controller = new ControllerImpl(model);

            ViewModel viewModel = new ViewModelImpl();
            ViewController viewController = new ViewControllerImpl(viewModel, controller);
            ViewObserver observer = new ViewDispatcherDI(dataBaseMenuReceiver, viewController);
            model.registerObserver(observer);
        });
    }
}
