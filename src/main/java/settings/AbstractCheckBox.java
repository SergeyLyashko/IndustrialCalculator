package settings;

import appview.Visitor;
import javax.swing.*;

abstract class AbstractCheckBox {

    private final JCheckBox jCheckBox;

    AbstractCheckBox(){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(320, 20);
    }

    void order(String type, Visitor visitor){
        SelectableCheckBox selectableCheckBox = createCheckBox(type);
        setLocation(selectableCheckBox);
        setName(selectableCheckBox);
        createListener(selectableCheckBox, visitor);
        visitor.addVisitorComponent(selectableCheckBox);
    }

    JCheckBox getComponentSwing(){
        return jCheckBox;
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

    private void createListener(SelectableCheckBox selectableCheckBox, Visitor visitor) {
        CheckBoxState checkBoxState = new CheckBoxState(selectableCheckBox, visitor);
        jCheckBox.addItemListener(checkBoxState);
    }

    abstract SelectableCheckBox createCheckBox(String type);
}
