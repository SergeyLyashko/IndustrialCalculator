package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Component("appPanel")
public class AppPanel implements Host {

    private final JPanel jPanel;
    private final CalculatorComponents calculatorComponents;
    private Visitor colorVisitor;

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        colorVisitor.addHost(this);
        addComponents();
    }

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
    }

    public AppPanel(CalculatorComponents calculatorComponents){
        this.calculatorComponents = calculatorComponents;
        jPanel = new JPanel();
        jPanel.setLayout(null);
    }

    private void addComponents() {
        List<AppComponent> components = calculatorComponents.getComponents();
        components.forEach(appComponent -> jPanel.add(appComponent.getComponentParent()));

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
    public JComponent getComponentParent() {
        return jPanel;
    }
}
