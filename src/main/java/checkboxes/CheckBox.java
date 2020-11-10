package checkboxes;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface CheckBox {

    default JCheckBox getCheckBox(){
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(320, 20);
        return jCheckBox;
    }

    SwingComponent create();

    default SwingComponent orderedCheckBox(Visitor visitor){
        SwingComponent selectableCheckBox = create();
        JCheckBox checkBox = getCheckBox();
        setLocation(selectableCheckBox, checkBox);
        setName(selectableCheckBox, checkBox);
        addListener(selectableCheckBox, checkBox, visitor);
        addVisitor(visitor, selectableCheckBox);
        selectableCheckBox.setParent(checkBox);
        return selectableCheckBox;
    }

    default void addVisitor(Visitor visitor, SwingComponent checkBox){
        visitor.addVisitorComponent(checkBox);
    }

    default void setLocation(SwingComponent selectableCheckBox, JCheckBox checkBox){
        int locationX = selectableCheckBox.getLocationX();
        int locationY = selectableCheckBox.getLocationY();
        checkBox.setLocation(locationX, locationY);
    }

    default void setName(SwingComponent selectableCheckBox, JCheckBox checkBox) {
        String boxName = selectableCheckBox.getName();
        checkBox.setText(boxName);
    }

    default void addListener(SwingComponent selectableCheckBox, JCheckBox checkBox, Visitor visitor) {
        CheckBoxState checkBoxState = new CheckBoxState(selectableCheckBox, visitor);
        checkBox.addItemListener(checkBoxState);
    }
}
