package appview;

import java.util.ArrayList;
import java.util.List;

class VisitorImpl implements Visitor {

    private final List<SwingComponent> componentsList = new ArrayList<>();

    @Override
    public void visit(SwingComponent component) {
        System.out.println("visit: "+component.getName());
    }

    public void raid() {
        componentsList.forEach(component -> component.acceptVisitor(this));
    }

    @Override
    public void addVisitorComponent(SwingComponent component) {
        componentsList.add(component);
    }
}
