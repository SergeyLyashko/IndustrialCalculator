package appcomponents;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisitorImpl implements Visitor {

    private final List<SwingComponent> componentsList = new CopyOnWriteArrayList<>();

    public void raid() {
        componentsList.forEach(component -> component.acceptVisitor(this));
    }

    @Override
    public void addVisitorComponent(SwingComponent component) {
        componentsList.add(component);
    }

    @Override
    public void visit(SwingComponent component) {
        System.out.println("visit component: "+ component.getName());
    }
}
