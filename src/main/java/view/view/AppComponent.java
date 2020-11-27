package view.view;

import view.controller.Visitor;

import javax.swing.*;

public interface AppComponent {

    default void integrationToPanel(){
        int locationX = getLocationX();
        int locationY = getLocationY();
        JComponent jComponent = getParent();
        jComponent.setLocation(locationX, locationY);
    }

    default void registerAsHost(Visitor visitor){}

    int getLocationX();

    int getLocationY();

    JComponent getParent();

    default boolean isFocused(){ return  false; }
}
