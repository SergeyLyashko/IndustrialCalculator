package view.view.info;

import view.controller.*;
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
        appComponent.integrationToPanel();
        ScrollWrapper scrollWrapper = new ScrollWrapper(viewController);
        AppComponent wrapperComponent = scrollWrapper.add(appComponent);
        wrapperComponent.integrationToPanel();
        return wrapperComponent;
    }

    @Override
    public List<AppComponent> createComponents() {
        Visitor controllerVisitor = viewController.getVisitor();
        integration(new Info(viewController), controllerVisitor);
        return components;
    }
}
