package info;

import appview.SwingComponent;
import appview.Visitor;

import java.util.ArrayList;
import java.util.List;

public class InfoComponent {

    private final List<SwingComponent> components = new ArrayList<>();

    public List<SwingComponent> getComponents(Visitor visitor){
        ScrollPanel scrollPanel = new ScrollPanel();
        components.add(scrollPanel.getComponent(visitor));
        return components;
    }
}
