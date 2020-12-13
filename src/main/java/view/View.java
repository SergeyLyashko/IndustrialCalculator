package view;

import controller.CalculatorModel;
import controller.ViewObserver;
import view.modelfieldsbehavior.BehaviorImpl;
import viewcontroller.ViewControllerImpl;
import view.viewmodel.Behavior;
import view.viewmodel.ViewModel;

public class View {

    public View(CalculatorModel model, DataBaseMenuReceiver dataBaseMenuReceiver, Controller controller){
        Behavior behavior = new BehaviorImpl();
        ViewModel viewModel = new ViewModel(controller, behavior);
        ViewController viewController = new ViewControllerImpl(viewModel);
        ViewObserver observer = new ViewDispatcherDI(dataBaseMenuReceiver, viewController);
        model.registerObserver(observer);
    }
}
