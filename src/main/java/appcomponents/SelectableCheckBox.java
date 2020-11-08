package appcomponents;

import java.awt.*;
import java.util.List;

public interface SelectableCheckBox extends SwingComponent {

    int getLocationX();

    int getLocationY();

    void select(Visitor visitor);

    void deselect(Visitor visitor);

    default List<SwingComponent> getComponents(){
        return null;
    }

    default LayoutManager getLayout() {return  null; }

    default String getBorderLayout(){ return  null; }

}
