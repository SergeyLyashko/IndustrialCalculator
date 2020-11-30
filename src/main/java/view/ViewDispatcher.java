package view;

import controller.Controller;
import model.CalculatorModel;
import view.controller.ViewController;
import view.controller.ViewControllerImpl;
import view.model.ViewModelImpl;
import view.view.View;

public class ViewDispatcher {

    public ViewDispatcher(MenuListReceiver menuListReceiver, CalculatorModel model, Controller controller) {

        ViewModelImpl viewModel = new ViewModelImpl(menuListReceiver);
        ViewController viewController = new ViewControllerImpl(viewModel);
        View view = new View(viewController);
    }
}
