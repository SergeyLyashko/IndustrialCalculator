package viewcomponents;

import java.util.ArrayList;
import java.util.List;

public class FactoryComponents {

    private final List<AppComponent> components = new ArrayList<>();

    public void addComponent(AppComponent swingComponent, Visitor visitor) {
        AbstractFactory factory = swingComponent.getFactory();
        AppComponent newComponent = factory.createNewComponent(swingComponent, visitor);
        components.add(newComponent);
    }

    public List<AppComponent> getComponents() {
        return components;
    }
}
