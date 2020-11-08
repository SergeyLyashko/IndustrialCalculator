package checkboxes;

import appcomponents.Visitor;

import javax.swing.*;

public abstract class AbstractCheckBox {

    private final JCheckBox jCheckBox;

    public AbstractCheckBox(){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(320, 20);
    }

    public abstract SelectableCheckBox create();

    public SelectableCheckBox orderedCheckBox(Visitor visitor){
        SelectableCheckBox selectableCheckBox = create();
        setLocation(selectableCheckBox);
        setName(selectableCheckBox);
        addListener(selectableCheckBox, visitor);
        addVisitor(visitor, selectableCheckBox);
        selectableCheckBox.setParent(jCheckBox);
        return selectableCheckBox;
    }

    private void addVisitor(Visitor visitor, SelectableCheckBox checkBox){
        visitor.addVisitorComponent(checkBox);
    }

    private void setLocation(SelectableCheckBox selectableCheckBox){
        int locationX = selectableCheckBox.getLocationX();
        int locationY = selectableCheckBox.getLocationY();
        jCheckBox.setLocation(locationX, locationY);
    }

    private void setName(SelectableCheckBox selectableCheckBox) {
        String boxName = selectableCheckBox.getName();
        jCheckBox.setText(boxName);
    }

    private void addListener(SelectableCheckBox selectableCheckBox, Visitor visitor) {
        CheckBoxState checkBoxState = new CheckBoxState(selectableCheckBox, visitor);
        jCheckBox.addItemListener(checkBoxState);
    }
}
