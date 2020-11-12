package appcomponents;

import java.util.List;

public interface SelectableComponent extends SwingComponent {

    void activate(Visitor visitor);

    void deactivate(Visitor visitor);

    default List<SwingComponent> getComponents(){
        return null;
    }

}
