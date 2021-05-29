package ui;

import javax.swing.*;

public interface UiComponent {

    JComponent getComponentParent();

    default String getName(){ return  null; }

    default boolean isTraversalPolicyFocused(){ return  false; }

    default int getFocusedRate(){ return 0; }

    default int compareTo(UiComponent component) { return 0; }
}
