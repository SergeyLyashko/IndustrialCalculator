package view;

import controller.ViewObserver;
import view.viewcontroller.ViewController;
import view.viewcontroller.ViewControllerImpl;
import view.viewmodel.ViewModel;
import view.viewframe.ViewDispatcherIoC;

public class View implements ViewObserver {

    private final ViewController viewController;

    public View(DataBaseMenuReceiver dataBaseMenuReceiver, Controller controller) {

        ViewModel viewModel = new ViewModel(controller);
        viewController = new ViewControllerImpl(viewModel);
        new ViewDispatcherIoC(viewController, dataBaseMenuReceiver);
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
