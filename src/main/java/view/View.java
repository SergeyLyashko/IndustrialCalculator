package view;

import controller.CalculatorModel;
import controller.ViewObserver;
import viewcontroller.ViewControllerImpl;
import view.viewmodel.ViewModelImpl;

public class View {

    public View(CalculatorModel model, DataBaseMenuReceiver dataBaseMenuReceiver, Controller controller){

        ViewModelImpl viewModelImpl = new ViewModelImpl();
        ViewController viewController = new ViewControllerImpl(viewModelImpl, controller);
        ViewObserver observer = new ViewDispatcherDI(dataBaseMenuReceiver, viewController);
        model.registerObserver(observer);
    }
}
