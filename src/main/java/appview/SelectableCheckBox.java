package appview;

import java.awt.*;
import java.util.List;

public interface SelectableCheckBox extends SwingComponent {

    int getLocationX();

    int getLocationY();

    void select();

    void deselect();

    default List<SwingComponent> getComponents(){
        return null;
    }

    default LayoutManager getLayout() {return  null; }

    default String getBorderLayout(){ return  null; }

}
