package comboboxes;

import appcomponents.SelectableComponent;
import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public interface AbstractComboBox extends SelectableComponent {

    int WIDTH = 155;
    int HEIGHT = 23;

    default JComboBox<String> getComboBox(){
        JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        return jComboBox;
    }

    default SwingComponent ordered(SwingComponent selectableComboBox, Visitor visitor){
        JComboBox<String> comboBox = getComboBox();
        setLocation(selectableComboBox, comboBox);
        addListener(selectableComboBox, comboBox);
        selectableComboBox.setParent(comboBox);
        return selectableComboBox;
    }

    default void addListener(SwingComponent selectableComboBox, JComboBox<String> comboBox) {
        ComboBoxState comboBoxState = new ComboBoxState(selectableComboBox);
        comboBox.addActionListener(comboBoxState);
    }

    default void setLocation(SwingComponent selectableComboBox, JComboBox<String> comboBox) {
        int locationX = selectableComboBox.getLocationX();
        int locationY = selectableComboBox.getLocationY();
        comboBox.setLocation(locationX, locationY);
    }
}
