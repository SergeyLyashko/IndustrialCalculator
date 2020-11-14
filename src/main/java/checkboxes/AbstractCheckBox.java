package checkboxes;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractCheckBox extends SwingComponent, CheckBoxSelectable {

    default SwingComponent initialization(SwingComponent component, Visitor visitor){
        JCheckBox jCheckBox = getCheckBox();
        setLocation(component, jCheckBox);
        setName(component, jCheckBox);
        addListener(component, jCheckBox, visitor);
        addVisitor(visitor, component);
        component.setParent(jCheckBox);
        return component;
    }

    default JCheckBox getCheckBox(){
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(320, 20);
        return jCheckBox;
    }

    default void addVisitor(Visitor visitor, SwingComponent component){
        visitor.addHost(component);
    }

    default void setLocation(SwingComponent component, JCheckBox jCheckBox){
        int locationX = component.getLocationX();
        int locationY = component.getLocationY();
        jCheckBox.setLocation(locationX, locationY);
    }

    default void setName(SwingComponent component, JCheckBox jCheckBox) {
        String boxName = component.getName();
        jCheckBox.setText(boxName);
    }

    default void addListener(SwingComponent selectableCheckBox, JCheckBox checkBox, Visitor visitor) {
        CheckBoxState checkBoxState = new CheckBoxState(selectableCheckBox, visitor);
        checkBox.addItemListener(checkBoxState);
    }
}
