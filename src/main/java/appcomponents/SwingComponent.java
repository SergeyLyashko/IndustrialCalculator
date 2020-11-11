package appcomponents;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface SwingComponent {

    int getLocationX();

    int getLocationY();

    String getName();

    List<SwingComponent> getComponents();

    void acceptVisitor(Visitor visitor);

    Container getParent();

    void setParent(JComponent jComponent);

    AbstractFactory getFactory();
}
