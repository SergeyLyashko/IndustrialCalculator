package appview;

import javax.swing.*;
import java.util.List;

public interface SelectableCheckBox extends SwingPanel {

    int getLocationX();

    int getLocationY();

    void select();

    void deselect();

    void setParentComponent(JCheckBox componentSwing);


    default List<SwingPanel> getComponents(Visitor visitor) {
        return null;
    }
}
