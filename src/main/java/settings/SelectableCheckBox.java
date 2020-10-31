package settings;

import appview.SwingComponent;

import javax.swing.*;

public interface SelectableCheckBox extends SwingComponent {

    int getLocationX();

    int getLocationY();

    //String getName();

    void select();

    void deselect();

    void setComponentSwing(JCheckBox componentSwing);
}
