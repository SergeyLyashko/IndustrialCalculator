package view;

import controller.Controller;
import controller.ViewObserver;
import model.CalculatorModel;
import view.controller.ViewController;
import view.controller.ViewControllerImpl;
import view.model.ViewModel;
import view.view.View;

public class ViewDispatcher implements ViewObserver {

    private final ViewController viewController;

    public ViewDispatcher(MenuListReceiver menuListReceiver, CalculatorModel appModel, Controller appController) {

        ViewModel viewModel = new ViewModel(appController);
        viewController = new ViewControllerImpl(viewModel);
        View view = new View(viewController, menuListReceiver);
    }

    @Override
    public void messageUpdate(String message, boolean alert) {
        viewController.setMessage(message, alert);
    }

    @Override
    public void resultUpdate(double result) {
        viewController.setResult(result);
    }
}
