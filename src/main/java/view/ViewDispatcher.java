package view;

import controller.Controller;
import model.CalculatorModel;
import view.controller.ViewController;
import view.controller.ViewControllerImpl;
import view.model.ViewModel;
import view.view.View;

public class ViewDispatcher {

    public ViewDispatcher(MenuListReceiver menuListReceiver, CalculatorModel appModel, Controller appController) {

        ViewModel viewModel = new ViewModel(appController);
        ViewController viewController = new ViewControllerImpl(viewModel);
        View view = new View(viewController, menuListReceiver);

    }
}
