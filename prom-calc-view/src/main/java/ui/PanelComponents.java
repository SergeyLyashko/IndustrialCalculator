package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public interface PanelComponents {

    List<JComponent> getPanelComponents();

    default List<FocusPolicy> getFocusableComponents(){
        return new ArrayList<>(0);
    }
}
