package ui;

import javax.swing.*;

public interface UiComponent {

    JComponent getComponentParent();

    default String getName(){ return  null; }
}
