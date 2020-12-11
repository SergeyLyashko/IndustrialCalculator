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
    private final ComponentsFactory componentsFactory;

    AppPanel(ComponentsFactory componentsFactory, ViewController viewController){
        this.componentsFactory = componentsFactory;
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
        List<AppComponent> components = componentsFactory.getComponents();
        components.forEach(appComponent -> jPanel.add(appComponent.getParent()));

    }

    void addFocusPolicy(CalculatorFocusTraversalPolicy focusTraversalPolicy){
        List<AppComponent> components = componentsFactory.getComponents();
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
