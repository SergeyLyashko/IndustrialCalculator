package components.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("focusPolicy")
public class CalculatorFocusTraversalPolicy extends FocusTraversalPolicy {

    private ArrayList<JComponent> thisOrder;

    @Autowired
    @Qualifier("Калькулятор")
    private AppPanel appPanel;

    @Autowired
    @Qualifier("Калькулятор компоненты")
    private CalculatorComponents calculatorComponents;

    @PostConstruct
    private void afterPropertiesSet() {
        List<AppComponent> components = calculatorComponents.getComponents();
        add(components);
        setFocusPolicy(appPanel);
    }

    private void add(List<AppComponent> componentList) {
        thisOrder = componentList.stream()
                .filter(AppComponent::isTraversalPolicyFocused)
                .sorted(AppComponent::compareTo)
                .map(AppComponent::getComponentParent)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void setFocusPolicy(AppPanel panel){
        JComponent parent = panel.getComponentParent();
        parent.setFocusCycleRoot(true);
        parent.setFocusTraversalPolicy(this);
    }

    @Override
    public Component getComponentAfter(Container container, Component component){
        int id = (thisOrder.indexOf(component) + 1) % thisOrder.size();
        return thisOrder.get(id);
    }

    @Override
    public Component getComponentBefore(Container container, Component component){
        int id = thisOrder.indexOf(component) - 1;
        if (id < 0){
            id = thisOrder.size() - 1;
        }
        return thisOrder.get(id);
    }

    @Override
    public Component getFirstComponent(Container container) {
        return thisOrder.get(0);
    }

    @Override
    public Component getLastComponent(Container container) {
        return thisOrder.get(thisOrder.size());
    }

    @Override
    public Component getDefaultComponent(Container container) {
        return thisOrder.get(0);
    }
}
