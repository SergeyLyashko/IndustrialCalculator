package view;

import javax.swing.*;

public interface AppComponent {

    JComponent getComponentParent();

    default String getName(){ return  null; }

    default boolean isTraversalPolicyFocused(){ return  false; }

    default int getFocusedRate(){ return 0; }

    default int compareTo(AppComponent component) { return 0; }

    default void addController(ViewController viewController) {}

}
