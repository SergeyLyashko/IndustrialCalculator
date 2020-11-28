package view.view;

import javax.swing.*;

public interface AppComponent {

    default void integrationToPanel(){
        int locationX = getLocationX();
        int locationY = getLocationY();
        JComponent jComponent = getParent();
        jComponent.setLocation(locationX, locationY);
    }

    int getLocationX();

    int getLocationY();

    JComponent getParent();

    default boolean isTraversalPolicyFocused(){ return  false; }
}
