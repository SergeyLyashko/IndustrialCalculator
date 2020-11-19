package view;

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

    default void registerHost(Visitor visitor){}

    int getLocationX();

    int getLocationY();

    JComponent getParent();

    default <T extends AppComponent> void addListener(T component){}

}
