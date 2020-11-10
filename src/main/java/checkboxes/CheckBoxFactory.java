package checkboxes;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

public class CheckBoxFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent component, Visitor visitor) {
        return ((CheckBox) () -> component).ordered(visitor);
    }
}