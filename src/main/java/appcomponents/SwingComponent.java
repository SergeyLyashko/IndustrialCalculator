package appcomponents;

import javax.swing.*;
import java.util.List;

public interface SwingComponent extends Host {

    int getLocationX();

    int getLocationY();

    String getName();

    default List<SwingComponent> getComponents(){
        return null;
    }

    JComponent getParent();

    <T extends JComponent> void setParent(T jComponent);

    AbstractFactory getFactory();
}
