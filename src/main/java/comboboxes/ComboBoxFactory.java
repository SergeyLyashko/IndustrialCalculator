package comboboxes;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

public class ComboBoxFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent component, Visitor visitor) {
        return ((ComboBox) () -> component).ordered(visitor);
    }
}
