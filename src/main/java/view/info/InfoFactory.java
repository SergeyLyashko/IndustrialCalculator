package view.info;

import view.*;

import java.util.ArrayList;
import java.util.List;

public class InfoFactory implements ComponentsFactory {

    private final List<AppComponent> components = new ArrayList<>();

    private void integration(AppComponent swingComponent, Visitor visitor) {
        swingComponent.integration(visitor);
        components.add(swingComponent);
    }

    @Override
    public List<AppComponent> getComponents() {
        return components;
    }

    @Override
    public void create(ReceivableMenu receivableMenu, Visitor visitor) {
        ScrollContainer scrollContainer = new ScrollContainer();
        scrollContainer.add(new Info(), visitor);
        integration(scrollContainer, visitor);
    }
}
