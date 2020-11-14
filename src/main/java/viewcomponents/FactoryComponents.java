package viewcomponents;

import java.util.ArrayList;
import java.util.List;

public class FactoryComponents {

    private final List<SwingComponent> components = new ArrayList<>();

    public void addComponent(SwingComponent swingComponent, Visitor visitor) {
        AbstractFactory factory = swingComponent.getFactory();
        SwingComponent newComponent = factory.createNewComponent(swingComponent, visitor);
        components.add(newComponent);
    }

    public List<SwingComponent> getComponents() {
        return components;
    }
}
