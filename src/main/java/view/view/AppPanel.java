package view.view;

import view.controller.Host;
import view.controller.ViewController;
import view.controller.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AppPanel implements Host {

    private final JPanel jPanel;

    public AppPanel(ComponentsFactory componentsFactory, ViewController viewController){
        List<AppComponent> components = componentsFactory.createComponents();
        jPanel = new JPanel();
        jPanel.setLayout(null);
        components.forEach(this::add);
        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
    }

    private void add(AppComponent component) {
        Container parentComponent = component.getParent();
        jPanel.add(parentComponent);
    }

    public void addFocusPolicy(ComponentsFactory componentsFactory){
        List<AppComponent> components = componentsFactory.createComponents();
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
