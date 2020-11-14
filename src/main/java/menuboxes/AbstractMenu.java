package menuboxes;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractMenu extends SwingComponent, MenuSelectable {

    int WIDTH = 155;
    int HEIGHT = 23;

    default JComboBox<String> getComboBox(){
        JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        return jComboBox;
    }

    default SwingComponent initialization(SwingComponent component, Visitor visitor){
        JComboBox<String> jComboBox = getComboBox();
        setLocation(component, jComboBox);
        addListener(component, visitor, jComboBox);
        component.setParent(jComboBox);
        return component;
    }

    // TODO components ??
    default void addListener(SwingComponent selectableComboBox, Visitor visitor, JComboBox<String> jComboBox) {
        MenuBehavior menuItemBehavior = new MenuBehavior();
        jComboBox.addActionListener(menuItemBehavior);
    }

    default void setLocation(SwingComponent component, JComboBox<String> jComboBox) {
        int locationX = component.getLocationX();
        int locationY = component.getLocationY();
        jComboBox.setLocation(locationX, locationY);
    }
}
