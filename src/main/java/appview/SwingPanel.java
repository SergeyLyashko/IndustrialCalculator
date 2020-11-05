package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface SwingPanel {

    String getName();

    List<SwingComponent> getComponents();

    void acceptVisitor(Visitor visitor);

    LayoutManager getLayout();

    String getBorderLayout();

    JComponent getParent();

    void setParent(JComponent jPanel);
}
