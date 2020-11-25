package view.view;

import view.controller.MenuReceivable;
import view.controller.ViewController;
import view.controller.Visitor;
import view.view.AppComponent;

import java.util.List;

public interface ComponentsFactory {

    // TODO получатель меню нужен только в 1 фабрике
    List<AppComponent> createComponents(ViewController viewController, Visitor visitor);
}
