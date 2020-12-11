package controller;

import database.DataBaseDispatcher;
import model.CalculatorFactory;
import model.ValueReceiver;
import model.CalculatorModelImpl;
import detailmass.CalculatorFactoryImpl;
import view.Controller;
import view.ViewDispatcherDI;
import view.DataBaseMenuReceiver;
import view.ViewController;
import view.viewcontroller.ViewControllerImpl;
import view.viewmodel.ViewModel;

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

            // TODO заменить на интерфейс ???
            ViewModel viewModel = new ViewModel(controller);
            ViewController viewController = new ViewControllerImpl(viewModel);
            ViewObserver observer = new ViewDispatcherDI(dataBaseMenuReceiver, viewController);

            model.registerObserver(observer);

        });
    }
}
