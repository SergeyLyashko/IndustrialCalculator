package appcomponents;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface SwingComponent extends Host {

    int getLocationX();

    int getLocationY();

    String getName();

    List<SwingComponent> getComponents();

    Container getParent();

    void setParent(JComponent jComponent);

    AbstractFactory getFactory();
}
