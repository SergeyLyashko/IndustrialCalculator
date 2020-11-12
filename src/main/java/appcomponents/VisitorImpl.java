package appcomponents;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisitorImpl implements Visitor {

    private final List<Host> componentsList = new CopyOnWriteArrayList<>();

    public void raid() {
        componentsList.forEach(component -> component.acceptVisitor(this));
    }

    @Override
    public void addHost(Host host) {
        componentsList.add(host);
    }

    @Override
    public void visit(Host host) {
        System.out.println("visit component: "+ host.getClass().getCanonicalName());//TEST
    }
}
