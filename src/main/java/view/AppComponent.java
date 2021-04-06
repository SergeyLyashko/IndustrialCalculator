package view;

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

    default String getName(){ return  null; }

    default boolean isTraversalPolicyFocused(){ return  false; }

    default int getFocusedRate(){ return 0; }

    default int compareTo(AppComponent component) { return 0; }

    default void addController(ViewController viewController) {}

    default void setLocation(int locationX, int locationY){}
}
