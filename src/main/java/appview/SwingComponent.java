package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface SwingComponent {

    String getName();

    List<SwingComponent> getComponents(Visitor visitor);

    void acceptVisitor(Visitor visitor);

    LayoutManager getLayout();

    String getBorderLayout();

    JComponent getSwingComponent();

}
