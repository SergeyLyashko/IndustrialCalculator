package checkboxes;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

public class CheckBoxFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent component, Visitor visitor) {
        AbstractCheckBox abstractCheckBox = new AbstractCheckBox() {
            @Override
            public SwingComponent create() {
                return component;
            }
        };
        return abstractCheckBox.orderedCheckBox(visitor);
    }
}
