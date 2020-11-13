package checkboxes;

import appcomponents.SelectableComponent;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractCheckBox extends SelectableComponent{

    default SwingComponent ordered(SwingComponent selectableCheckBox, Visitor visitor){
        JCheckBox checkBox = getCheckBox();
        setLocation(selectableCheckBox, checkBox);
        setName(selectableCheckBox, checkBox);
        addListener(selectableCheckBox, checkBox, visitor);
        addVisitor(visitor, selectableCheckBox);
        selectableCheckBox.setParent(checkBox);
        return selectableCheckBox;
    }

    default JCheckBox getCheckBox(){
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(320, 20);
        return jCheckBox;
    }

    default void addVisitor(Visitor visitor, SwingComponent checkBox){
        visitor.addHost(checkBox);
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
