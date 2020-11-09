package fields;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

class FieldFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent component, Visitor visitor) {
        AbstractField abstractField = new AbstractField() {
            @Override
            public SwingComponent create() {
                return component;
            }
        };
        return abstractField.orderedField();
    }
}
