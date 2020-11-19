package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalculatorFocusTraversalPolicy extends FocusTraversalPolicy {

    private final ArrayList<JComponent> thisOrder;

    public CalculatorFocusTraversalPolicy(ArrayList<JComponent> order) {
        this.thisOrder = new ArrayList<>(order.size());
        thisOrder.addAll(order);
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
