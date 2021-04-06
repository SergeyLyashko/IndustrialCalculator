package view;

import javax.swing.*;
import java.util.List;

class AppPanel implements Host {

    private final JPanel jPanel;
    private final CalculatorComponents calculatorComponents;

    AppPanel(CalculatorComponents calculatorComponents, ViewController viewController){
        this.calculatorComponents = calculatorComponents;
        jPanel = new JPanel();
        jPanel.setLayout(null);
        addComponents();
        addHost(viewController);
    }

    private void addHost(ViewController viewController){
        Visitor visitor = viewController.getVisitor();
        visitor.addHost(this);
    }

    private void addComponents() {
        List<AppComponent> components = calculatorComponents.getComponents();
        components.forEach(appComponent -> jPanel.add(appComponent.getParent()));

    }

    void addFocusPolicy(CalculatorFocusTraversalPolicy focusTraversalPolicy){
        List<AppComponent> components = calculatorComponents.getComponents();
        focusTraversalPolicy.add(components);
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
