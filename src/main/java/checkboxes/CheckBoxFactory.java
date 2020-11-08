package checkboxes;

import appcomponents.SelectableCheckBox;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxFactory {

    private final List<SwingComponent> components = new ArrayList<>();

    public void addComponent(SelectableCheckBox checkBox, Visitor visitor){
        SelectableCheckBox selectableCheckBox = createCheckBox(checkBox, visitor);
        components.add(selectableCheckBox);
    }

    private SelectableCheckBox createCheckBox(SelectableCheckBox checkBox, Visitor visitor) {
        AbstractCheckBox abstractCheckBox = new AbstractCheckBox() {
            @Override
            public SelectableCheckBox create() {
                return checkBox;
            }
        };
        return abstractCheckBox.orderedCheckBox(visitor);
    }

    public List<SwingComponent> getComponents(){
        return components;
    }
}
