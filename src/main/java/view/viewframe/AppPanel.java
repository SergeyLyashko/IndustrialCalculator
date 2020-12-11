package view.viewframe;

import view.AppComponent;
import view.ComponentsFactory;
import view.Host;
import view.Visitor;
import view.viewcontroller.ViewController;

import javax.swing.*;
import java.util.List;

class AppPanel implements Host {

    private final JPanel jPanel;

    AppPanel(ComponentsFactory componentsFactory, ViewController viewController){
        jPanel = new JPanel();
        jPanel.setLayout(null);
        List<AppComponent> components = componentsFactory.getComponents();
        components.forEach(this::add);
        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
    }

    private void add(AppComponent component) {
        JComponent parent = component.getParent();
        jPanel.add(parent);
    }

    void addFocusPolicy(ComponentsFactory componentsFactory){
        List<AppComponent> components = componentsFactory.getComponents();
        CalculatorFocusTraversalPolicy focusTraversalPolicy = new CalculatorFocusTraversalPolicy(components);
        focusTraversalPolicy.setFocusPolicy(this);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getParent() {
        return jPanel;
    }
}
