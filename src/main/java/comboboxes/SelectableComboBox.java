package comboboxes;

import appcomponents.SwingComponent;

import java.util.List;

public interface SelectableComboBox extends SwingComponent {

    default List<SwingComponent> getComponents(){ return null; }

}
