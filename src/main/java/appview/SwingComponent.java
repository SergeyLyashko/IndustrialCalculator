package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface SwingComponent {

    String getName();

    List<SwingComponent> getComponents();

    void acceptVisitor(Visitor visitor);

    LayoutManager getLayout();

    JComponent getParent();
}
