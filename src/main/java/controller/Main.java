package controller;

import database.DetailsDAO;
import model.CalculatorModelImpl;
import model.View;
import viewcontroller.Controller;
import view.DataReceiver;
import view.ViewController;
import view.ViewImpl;
import viewcontroller.ViewControllerImpl;
import viewcontroller.ViewModel;
import viewmodel.ViewModelImpl;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            DataReceiver dataReceiver = new DetailsDAO();

            CalculatorModel model = new CalculatorModelImpl();
            Controller controller = new ControllerImpl(model, dataReceiver);

            ViewModel viewModel = new ViewModelImpl();
            ViewController viewController = new ViewControllerImpl(viewModel, controller);
            View view = new ViewImpl(dataReceiver, viewController);

            model.registerObserver(view);
            controller.registerObserver(view);
        });
    }
}
