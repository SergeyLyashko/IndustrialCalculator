package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface SwingPanel {

    String getName();

    SwingPanel getPanel(Visitor visitor);

    List<SwingComponent> getComponents();

    void acceptVisitor(Visitor visitor);


    default LayoutManager getLayout() {
        return null;
    }

    default String getBorderLayout() {
        return null;
    }

    default JComponent getParent() {
        return null;
    }

    default void setParent(JComponent jPanel) {}

}
