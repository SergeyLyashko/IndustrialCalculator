package infocomponents;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InfoComponents {

    private List<SwingComponent> components;

    public void addComponent(SwingComponent component, Visitor visitor) {
        components = createContainer(component, visitor);
    }

    private List<SwingComponent> createContainer(SwingComponent components, Visitor visitor){
        Scroll scroll = new Scroll();
        SwingComponent container = scroll.createContainer(components, visitor);
        return Stream.of(container).collect(Collectors.toList());
    }

    public List<SwingComponent> getComponents(){
        return components;
    }
}
