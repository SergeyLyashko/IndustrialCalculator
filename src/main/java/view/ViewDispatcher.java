package view;

import view.controller.MenuReceivable;
import view.controller.ViewController;
import view.controller.ViewControllerImpl;
import view.model.ViewModel;
import view.model.ViewModelInterface;
import view.view.View;

public class ViewDispatcher {

    private final MenuReceivable menuReceivable;

    public ViewDispatcher(MenuReceivable menuReceivable) {
        this. menuReceivable = menuReceivable;
    }

    public void createVew() {
        ViewModelInterface viewModel = new ViewModel(menuReceivable);
        ViewController viewController = new ViewControllerImpl(viewModel);
        View view = new View(viewModel, viewController);
        view.createView();
    }
}
