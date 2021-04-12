package viewcomponents.common;

import javax.swing.*;

public interface Host {

    void acceptVisitor(Visitor visitor);

    default JComponent getScrollViewPort(){ return null; }

    JComponent getComponentParent();
}