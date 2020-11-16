package view;

import javax.swing.*;

public interface AppComponent extends Host {

    default void integration(Visitor visitor){
        JComponent jComponent = getParent();
        setLocation(jComponent);
        addListener(visitor);
        addVisitor(visitor);
    }

    default void setLocation(JComponent jComponent) {
        int locationX = getLocationX();
        int locationY = getLocationY();
        jComponent.setLocation(locationX, locationY);
    }

    default void addVisitor(Visitor visitor){
        visitor.addHost(this);
    }

    void addListener(Visitor visitor);

    int getLocationX();

    int getLocationY();

    JComponent getParent();
}
