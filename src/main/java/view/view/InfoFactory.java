package view.view;

import view.controller.AppComponent;
import view.controller.ComponentsFactory;
import view.controller.MenuReceivable;
import view.controller.Visitor;
import view.model.ScrollWrapper;
import view.view.Info;

import java.util.ArrayList;
import java.util.List;

public class InfoFactory implements ComponentsFactory {

    private final List<AppComponent> components = new ArrayList<>();

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
    public List<AppComponent> createComponents(MenuReceivable menuReceivable, Visitor visitor) {
        integration(new Info(), visitor);
        return components;
    }
}
