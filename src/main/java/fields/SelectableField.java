package fields;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.awt.*;
import java.util.List;

public interface SelectableField extends SwingComponent {

    int getLocationX();

    int getLocationY();

    default List<SwingComponent> getComponents(){ return null; }

    default void acceptVisitor(Visitor visitor){}

    default LayoutManager getLayout(){ return  null; }

    default String getBorderLayout() { return  null; }

}
