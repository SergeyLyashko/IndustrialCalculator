package view;

import controller.CalculatorModel;
import controller.ViewObserver;
import viewcontroller.ViewControllerImpl;
import viewmodel.ViewModelImpl;
import viewcontroller.ViewModel;

public class View {

    public View(CalculatorModel model, DataBaseMenuReceiver dataBaseMenuReceiver, Controller controller){


        ViewModel viewModel = new ViewModelImpl();
        ViewController viewController = new ViewControllerImpl(viewModel, controller);
        ViewObserver observer = new ViewDispatcherDI(dataBaseMenuReceiver, viewController);
        model.registerObserver(observer);
    }
}
