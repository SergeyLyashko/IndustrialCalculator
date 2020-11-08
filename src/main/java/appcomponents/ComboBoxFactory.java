package appcomponents;

import comboboxes.AbstractComboBox;
import comboboxes.SelectableComboBox;

public class ComboBoxFactory {

    private SelectableComboBox selectableComboBox;

    public void createNewComboBox(SelectableComboBox comboBoxClassImpl){
        selectableComboBox = create(comboBoxClassImpl);
    }

    private SelectableComboBox create(SelectableComboBox comboBoxClassImpl) {
        AbstractComboBox abstractComboBox = new AbstractComboBox() {
            @Override
            public SelectableComboBox create() {
                return comboBoxClassImpl;
            }
        };
        return abstractComboBox.orderedComboBox();
    }

    public SelectableComboBox getComponent(){
        return selectableComboBox;
    }
}
