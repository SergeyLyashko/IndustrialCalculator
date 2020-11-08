package appcomponents;

import checkboxes.SelectableCheckBox;

import java.util.ArrayList;
import java.util.List;

public class SettingsComponents {

    private final List<SwingComponent> components = new ArrayList<>();

    public void addComponent(SelectableCheckBox checkBox, Visitor visitor) {
        CheckBoxFactory checkBoxFactory = new CheckBoxFactory();
        checkBoxFactory.createNewCheckBox(checkBox, visitor);
        SwingComponent component = checkBoxFactory.getComponent();
        components.add(component);
    }

    public List<SwingComponent> getComponents(){
        return components;
    }

}
