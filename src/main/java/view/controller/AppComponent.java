package view.controller;

import view.model.CalculatorFieldState;

import javax.swing.*;

public interface AppComponent {

    default void integration(){
        JComponent jComponent = getParent();
        setLocation(jComponent);
    }

    default void setLocation(JComponent jComponent) {
        int locationX = getLocationX();
        int locationY = getLocationY();
        jComponent.setLocation(locationX, locationY);
    }

    default void registerAsHost(Visitor visitor){}

    default int getLocationX(){ return 0; };

    default int getLocationY() { return 0; }

    JComponent getParent();

    default void addMenuListener(MenuSelectable menuSelectable){}

    default void addFieldStateListener(CalculatorFieldState calculatorFieldState){}

    default boolean isFocused(){ return  false; }
}
