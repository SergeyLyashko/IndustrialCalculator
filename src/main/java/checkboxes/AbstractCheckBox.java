package checkboxes;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public abstract class AbstractCheckBox {

    private final JCheckBox jCheckBox;

    public AbstractCheckBox(){
        jCheckBox = new JCheckBox();
        jCheckBox.setSelected(true);
        jCheckBox.setSize(320, 20);
    }

    public abstract SwingComponent create();

    public SwingComponent orderedCheckBox(Visitor visitor){
        SwingComponent selectableCheckBox = create();
        setLocation(selectableCheckBox);
        setName(selectableCheckBox);
        addListener(selectableCheckBox, visitor);
        addVisitor(visitor, selectableCheckBox);
        selectableCheckBox.setParent(jCheckBox);
        return selectableCheckBox;
    }

    private void addVisitor(Visitor visitor, SwingComponent checkBox){
        visitor.addVisitorComponent(checkBox);
    }

    private void setLocation(SwingComponent selectableCheckBox){
        int locationX = selectableCheckBox.getLocationX();
        int locationY = selectableCheckBox.getLocationY();
        jCheckBox.setLocation(locationX, locationY);
    }

    private void setName(SwingComponent selectableCheckBox) {
        String boxName = selectableCheckBox.getName();
        jCheckBox.setText(boxName);
    }

    private void addListener(SwingComponent selectableCheckBox, Visitor visitor) {
        CheckBoxState checkBoxState = new CheckBoxState(selectableCheckBox, visitor);
        jCheckBox.addItemListener(checkBoxState);
    }
}
