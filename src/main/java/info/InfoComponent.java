package info;

import appview.ContainerBuilder;
import appview.SwingComponent;
import appview.Visitor;

import java.util.ArrayList;
import java.util.List;

public class InfoComponent {

    private final List<SwingComponent> components = new ArrayList<>();

    public List<SwingComponent> getComponents(Visitor visitor){
        ContainerBuilder containerBuilder = new ContainerBuilder();
        SwingComponent scroll = containerBuilder.build("Scroll", visitor);
        components.add(scroll);
        return components;
    }
}
