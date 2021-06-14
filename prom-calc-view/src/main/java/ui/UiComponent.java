package ui;

import javax.swing.*;

public interface UiComponent {

    JComponent getComponent();

    default String getName(){ return  null; }
}
