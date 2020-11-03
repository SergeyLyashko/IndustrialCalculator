package appview;

import javax.swing.*;
import java.util.List;

public interface SelectableCheckBox extends SwingPanel {

    int getLocationX();

    int getLocationY();

    void select();

    void deselect();

    void setParentComponent(JCheckBox componentSwing);


    default SwingPanel getPanel(Visitor visitor) {
        return null;
    }

    default List<SwingPanel> getComponents(){
        return null;
    }
}
