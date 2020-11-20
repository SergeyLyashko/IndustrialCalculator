package view.info;

import view.*;

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
    public List<AppComponent> getComponentList() {
        return components;
    }

    @Override
    public void create(MenuReceivable menuReceivable, Visitor visitor) {
        integration(new Info(), visitor);
    }
}
