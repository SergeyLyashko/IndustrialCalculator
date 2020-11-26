package view.view.info;

import view.controller.*;
import view.model.ScrollWrapper;
import view.view.AppComponent;
import view.view.ComponentsFactory;

import java.util.ArrayList;
import java.util.List;

public class InfoFactory implements ComponentsFactory {

    private final List<AppComponent> components;
    private final ViewController viewController;

    public InfoFactory(ViewController viewController) {
        this.viewController = viewController;
        components = new ArrayList<>();
    }

    private void integration(AppComponent appComponent, Visitor visitor) {
        AppComponent wrapperComponent = wrap(appComponent, visitor);
        components.add(wrapperComponent);
    }

    private AppComponent wrap(AppComponent appComponent, Visitor visitor){
        appComponent.integration();
        appComponent.registerAsHost(visitor);
        ScrollWrapper scrollWrapper = new ScrollWrapper();
        AppComponent wrapperComponent = scrollWrapper.add(appComponent);
        wrapperComponent.integration();
        wrapperComponent.registerAsHost(visitor);
        return wrapperComponent;
    }

    @Override
    public List<AppComponent> createComponents() {
        Visitor controllerVisitor = viewController.getVisitor();
        integration(new Info(), controllerVisitor);
        return components;
    }
}
