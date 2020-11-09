package comboboxes;

import appcomponents.SwingComponent;

import javax.swing.*;

public abstract class AbstractComboBox {

    private final JComboBox<String> jComboBox;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;

    public AbstractComboBox(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
    }

    public abstract SwingComponent create();

    public SwingComponent orderedComboBox(){
        SwingComponent selectableComboBox = create();
        setLocation(selectableComboBox);
        addListener(selectableComboBox);
        selectableComboBox.setParent(jComboBox);
        return selectableComboBox;
    }

    private void addListener(SwingComponent selectableComboBox) {
        ComboBoxState comboBoxState = new ComboBoxState(selectableComboBox);
        jComboBox.addActionListener(comboBoxState);
    }

    private void setLocation(SwingComponent selectableComboBox) {
        int locationX = selectableComboBox.getLocationX();
        int locationY = selectableComboBox.getLocationY();
        jComboBox.setLocation(locationX, locationY);
    }

}
