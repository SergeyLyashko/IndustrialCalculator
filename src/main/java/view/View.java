package view;

import controller.CalculatorModel;
import controller.ViewObserver;
import view.modelfieldsbehavior.BehaviorImpl;
import view.viewmodel.ColorChangeVisitor;
import viewcontroller.ViewControllerImpl;
import view.viewmodel.Behavior;
import view.viewmodel.ViewModelImpl;

public class View {

    public View(CalculatorModel model, DataBaseMenuReceiver dataBaseMenuReceiver, Controller controller){

        Behavior behavior = new BehaviorImpl();
        ViewModelImpl viewModelImpl = new ViewModelImpl(controller, behavior);
        Visitor colorVisitor = new ColorChangeVisitor();

        ViewController viewController = new ViewControllerImpl(viewModelImpl, colorVisitor);

        ViewObserver observer = new ViewDispatcherDI(dataBaseMenuReceiver, viewController);

        model.registerObserver(observer);
    }
}
