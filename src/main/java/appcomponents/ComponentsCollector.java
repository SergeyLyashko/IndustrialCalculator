package appcomponents;

import java.util.ArrayList;
import java.util.List;

// TODO нужен ли класс ??? заменить на List<SwingComponents> ????
public class ComponentsCollector {

    private final List<SwingComponent> swingComponents = new ArrayList<>();

    public void addComponent(SwingComponent swingComponent, Visitor visitor) {
        AbstractFactory factory = swingComponent.getFactory();
        SwingComponent newComponent = factory.createNewComponent(swingComponent, visitor);
        swingComponents.add(newComponent);
    }

    public List<SwingComponent> getComponents() {
        return swingComponents;
    }
}
