package ui;

import javax.swing.*;

public interface Colorizeble extends UiComponent {

    void acceptColorChanger(ColorChanger colorChanger);

    default JComponent getScrollViewPort(){ return null; }
}
