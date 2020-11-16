package view.info;

import view.*;

import java.util.ArrayList;
import java.util.List;

public class InfoFactory implements ComponentsFactory {

    private final List<AppComponent> components = new ArrayList<>();

    private void addInit(AppComponent swingComponent, Visitor visitor) {
        Integrator initializer = swingComponent.getIntegrator();
        AppComponent appComponent = initializer.integration(swingComponent, visitor);
        components.add(appComponent);
    }

    @Override
    public List<AppComponent> getComponents() {
        return components;
    }

    @Override
    public void create(MenuReceiver menuReceiver, Visitor visitor) {
        addInit(new Info(), visitor);
    }
}
