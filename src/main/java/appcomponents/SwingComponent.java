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

    LayoutManager getLayout();

    String getBorderLayout();

    Container getParent();

    void setParent(JComponent jComponent);

    Factory getFactory();
}
