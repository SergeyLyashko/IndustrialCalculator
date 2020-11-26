package view.view;

import view.controller.Host;
import view.controller.Visitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AppPanel implements Host {

    private final JPanel jPanel;

    public AppPanel(List<AppComponent> components, Visitor visitor){
        jPanel = new JPanel();
        jPanel.setLayout(null);
        components.forEach(this::add);
        visitor.addHost(this);
    }

    private void add(AppComponent component) {
        Container parentComponent = component.getParent();
        jPanel.add(parentComponent);
    }

    public void addFocusPolicy(List<AppComponent> components){
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
