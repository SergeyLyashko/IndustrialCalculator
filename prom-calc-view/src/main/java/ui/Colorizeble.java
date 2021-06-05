package ui;

import javax.swing.*;

public interface Colorizeble {

    void acceptVisitor(ColorChanger colorChanger);

    default JComponent getScrollViewPort(){ return null; }

    JComponent getComponentParent();
}
