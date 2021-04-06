package view;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component("appPanel")
public class AppPanel implements Host, InitializingBean {

    private final JPanel jPanel;
    private final CalculatorComponents calculatorComponents;
    private ViewController viewController;
    private Visitor colorVisitor;

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addHost(viewController);
    }

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    public AppPanel(CalculatorComponents calculatorComponents/*, ViewController viewController*/){
        this.calculatorComponents = calculatorComponents;
        jPanel = new JPanel();
        jPanel.setLayout(null);
        addComponents();
        //addHost(viewController);
    }

    private void addHost(ViewController viewController){
        //Visitor visitor = viewController.getVisitor();
        colorVisitor.addHost(this);
    }

    private void addComponents() {
        List<AppComponent> components = calculatorComponents.getComponents();
        components.forEach(appComponent -> jPanel.add(appComponent.getParent()));

    }

    public void addFocusPolicy(CalculatorFocusTraversalPolicy focusTraversalPolicy){
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
