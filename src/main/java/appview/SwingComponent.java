package appview;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface SwingComponent {

    String getName();

    List<SwingComponent> getComponents(Visitor visitor);

    void acceptVisitor(Visitor visitor);

    default LayoutManager getLayout() {
        return null;
    }

    default String getBorderLayout() {
        return null;
    }

    default JComponent getParentsComponent() {
        return null;
    }
}
