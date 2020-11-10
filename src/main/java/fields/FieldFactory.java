package fields;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

class FieldFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent component, Visitor visitor) {
        return ((Field) () -> component).ordered(visitor);
    }
}
