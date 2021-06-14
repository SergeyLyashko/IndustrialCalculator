package ui;

import java.util.ArrayList;
import java.util.List;

public interface PanelComponents {

    List<UiComponent> getPanelComponents();

    default List<FocusPolicy> getFocusableComponents(){
        return new ArrayList<>(0);
    }
}
