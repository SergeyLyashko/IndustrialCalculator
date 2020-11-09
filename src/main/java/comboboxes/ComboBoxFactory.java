package comboboxes;

import appcomponents.Factory;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

public class ComboBoxFactory implements Factory {

    public SwingComponent createNewComponent(SwingComponent component, Visitor visitor) {
        AbstractComboBox abstractComboBox = new AbstractComboBox() {
            @Override
            public SwingComponent create() {
                return component;
            }
        };
        return abstractComboBox.orderedComboBox();
    }
}
