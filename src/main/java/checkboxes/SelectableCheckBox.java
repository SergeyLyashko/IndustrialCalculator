package checkboxes;

import appcomponents.SwingComponent;
import appcomponents.Visitor;
import java.util.List;

public interface SelectableCheckBox extends SwingComponent {

    void select(Visitor visitor);

    void deselect(Visitor visitor);

    default List<SwingComponent> getComponents(){
        return null;
    }

}
