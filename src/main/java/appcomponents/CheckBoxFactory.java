package appcomponents;

import appcomponents.SwingComponent;
import appcomponents.Visitor;
import checkboxes.AbstractCheckBox;
import checkboxes.SelectableCheckBox;

public class CheckBoxFactory {

    private SelectableCheckBox selectableCheckBox;

    public void createNewCheckBox(SelectableCheckBox checkBox, Visitor visitor){
        selectableCheckBox = create(checkBox, visitor);
    }

    private SelectableCheckBox create(SelectableCheckBox checkBoxClassImpl, Visitor visitor) {
        AbstractCheckBox abstractCheckBox = new AbstractCheckBox() {
            @Override
            public SelectableCheckBox create() {
                return checkBoxClassImpl;
            }
        };
        return abstractCheckBox.orderedCheckBox(visitor);
    }

    public SwingComponent getComponent(){
        return selectableCheckBox;
    }
}
