package ui.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ui.FocusPolicy;
import ui.PanelComponents;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("focusPolicy")
class CalculatorFocusTraversalPolicy extends FocusTraversalPolicy {

    private ArrayList<JComponent> thisOrder;

    @Autowired
    @Qualifier("Калькулятор")
    private JPanel appPanel;

    @Autowired
    @Qualifier("Калькулятор конфигурация")
    private PanelComponents calculatorPanelComponents;

    @PostConstruct
    private void afterPropertiesSet() {
        List<FocusPolicy> focusableComponents = calculatorPanelComponents.getFocusableComponents();
        add(focusableComponents);
        setFocusPolicy(appPanel);
    }

    private void add(List<FocusPolicy> focusPolicyList) {
        thisOrder = focusPolicyList.stream()
                .sorted(Comparator.comparing(FocusPolicy::getFocusRate))
                .map(FocusPolicy::getComponent)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void setFocusPolicy(JPanel panel){
        panel.setFocusCycleRoot(true);
        panel.setFocusTraversalPolicy(this);
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
