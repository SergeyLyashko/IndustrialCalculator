package appcomponents;

import checkboxes.SelectableCheckBox;
import comboboxes.SelectableComboBox;

import java.util.ArrayList;
import java.util.List;

public class CalculatorComponents {

    private final List<SwingComponent> swingComponents = new ArrayList<>();

    public void addComponent(SelectableCheckBox checkBox, Visitor visitor) {
        CheckBoxFactory checkBoxFactory = new CheckBoxFactory();
        checkBoxFactory.createNewCheckBox(checkBox, visitor);
        SwingComponent component = checkBoxFactory.getComponent();
        swingComponents.add(component);
    }

    public void addComponent(SelectableComboBox comboBox) {
        ComboBoxFactory comboBoxFactory = new ComboBoxFactory();
        comboBoxFactory.createNewComboBox(comboBox);
        SelectableComboBox component = comboBoxFactory.getComponent();
        swingComponents.add(component);
    }

    public List<SwingComponent> getComponents() {
        return swingComponents;
    }

}
