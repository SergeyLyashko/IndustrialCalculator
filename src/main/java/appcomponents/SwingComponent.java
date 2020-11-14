package appcomponents;

import javax.swing.*;

public interface SwingComponent extends Host {

    default void addVisitor(Visitor visitor, SwingComponent component){
        visitor.addHost(component);
    }

    default void setLocation(SwingComponent swingComponent, JComponent component) {
        int locationX = swingComponent.getLocationX();
        int locationY = swingComponent.getLocationY();
        component.setLocation(locationX, locationY);
    }

    default SwingComponent initialization(SwingComponent component, Visitor visitor){
        JComponent jComponent = getParent();
        setLocation(component, jComponent);
        addListener(component, visitor);
        addVisitor(visitor, component);
        return component;
    }

    default AbstractFactory getFactory(){
        return this::initialization;
    }

    void addListener(SwingComponent component, Visitor visitor);

    int getLocationX();

    int getLocationY();

    JComponent getParent();
}
