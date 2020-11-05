package appview;

import javax.swing.*;
import java.util.List;

public interface SelectableCheckBox extends SwingComponent {

    int getLocationX();

    int getLocationY();

    void select();

    void deselect();

    void setParentComponent(JCheckBox componentSwing);

    default List<SwingComponent> getComponents(){
        return null;
    }

}
