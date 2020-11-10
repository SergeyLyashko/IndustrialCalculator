package fields;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

class FieldFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent component, Visitor visitor) {
        // TODO DELETE
        /*AbstractField abstractField = new AbstractField() {
            @Override
            public SwingComponent create() {
                return component;
            }
        };
        return abstractField.orderedField();*/
        return ((Field) () -> component).orderedField();
    }
}
