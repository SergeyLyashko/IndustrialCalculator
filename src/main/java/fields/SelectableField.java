package fields;

import appcomponents.SwingComponent;

import java.util.List;

public interface SelectableField extends SwingComponent {

    default List<SwingComponent> getComponents(){ return null; }

}
