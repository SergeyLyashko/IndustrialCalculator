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
        integration(new Info(viewController));
    }

    private void integration(AppComponent component) {
        component.integrationToPanel();
        wrap(component);
    }

    private void wrap(AppComponent appComponent){
        ScrollWrapper scrollWrapper = new ScrollWrapper(viewController, appComponent);
        scrollWrapper.integrationToPanel();
        components.add(scrollWrapper);
    }

    @Override
    public List<AppComponent> getComponents(){
        return components;
    }
}
