package view;

import controller.ViewObserver;
import view.controller.ViewController;
import view.controller.ViewControllerImpl;
import view.model.ViewModel;
import view.view.ViewDispatcher;

public class View implements ViewObserver {

    private final ViewController viewController;

    public View(DataBaseMenuReceiver dataBaseMenuReceiver, Controller controller) {
        
        ViewModel viewModel = new ViewModel(controller);
        viewController = new ViewControllerImpl(viewModel);
        new ViewDispatcher(viewController, dataBaseMenuReceiver);
    }

    @Override
    public void messageUpdate(String message, boolean alert) {
        viewController.setMessage(message, alert);
    }

    @Override
    public void resultUpdate(String result, boolean alert) {
        viewController.setResult(result, alert);
    }
}
