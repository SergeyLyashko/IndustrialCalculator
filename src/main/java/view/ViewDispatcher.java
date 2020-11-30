package view;

import controller.Controller;
import model.CalculatorModel;
import view.controller.ViewController;
import view.controller.ViewControllerImpl;
import view.model.ViewModelImpl;
import view.model.ViewModel;
import view.view.View;

public class ViewDispatcher {

    public ViewDispatcher(MenuListReceivable menuListReceivable, CalculatorModel model, Controller controller) {

        ViewModelImpl viewModel = new ViewModelImpl(menuListReceivable);
        ViewController viewController = new ViewControllerImpl(viewModel);
        View view = new View(viewController);
    }
}
