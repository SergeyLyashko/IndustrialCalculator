package appview;

import java.util.ArrayList;
import java.util.List;

class VisitorImpl implements Visitor {

    private final List<SwingPanel> componentsList = new ArrayList<>();

    @Override
    public void visit(SwingPanel component) {
        System.out.println("visit: "+component.getName());
    }

    public void raid() {
        componentsList.forEach(component -> component.acceptVisitor(this));
    }

    @Override
    public void addVisitorComponent(SwingPanel component) {
        componentsList.add(component);
    }
}
