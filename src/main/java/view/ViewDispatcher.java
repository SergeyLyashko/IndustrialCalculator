package view;

import view.controller.MenuReceivable;
import view.controller.ViewController;
import view.controller.ViewControllerImpl;
import view.model.ViewModelImpl;
import view.model.ViewModel;
import view.view.View;

public class ViewDispatcher {

    public ViewDispatcher(MenuReceivable menuReceivable) {
        ViewModel viewModel = new ViewModelImpl(menuReceivable);
        ViewController viewController = new ViewControllerImpl(viewModel);
        View view = new View(viewModel, viewController);
    }
}
