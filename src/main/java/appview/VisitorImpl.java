package appview;

import java.util.ArrayList;
import java.util.List;

class VisitorImpl implements Visitor {

    private final List<SwingPanel> panelsList = new ArrayList<>();
    private final List<SwingComponent> componentsList = new ArrayList<>();

    @Override
    public void visit(SwingPanel panel) {
        System.out.println("visit panel: "+ panel.getName());
    }

    public void raid() {
        panelsList.forEach(component -> component.acceptVisitor(this));
        componentsList.forEach(component -> component.acceptVisitor(this));
    }

    @Override
    public void addVisitorPanel(SwingPanel panel) {
        panelsList.add(panel);
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
