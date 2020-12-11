package view;

import javax.swing.*;
import java.util.List;

class AppPanel implements Host {

    private final JPanel jPanel;
    private final ComponentsList componentsList;

    AppPanel(ComponentsList componentsList, ViewController viewController){
        this.componentsList = componentsList;
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
        List<AppComponent> components = componentsList.getComponents();
        components.forEach(appComponent -> jPanel.add(appComponent.getParent()));

    }

    void addFocusPolicy(CalculatorFocusTraversalPolicy focusTraversalPolicy){
        List<AppComponent> components = componentsList.getComponents();
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
