package comboboxes;

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

    public abstract SelectableComboBox create();

    public SelectableComboBox orderedComboBox(){
        SelectableComboBox selectableComboBox = create();
        setLocation(selectableComboBox);
        addListener(selectableComboBox);
        selectableComboBox.setParent(jComboBox);
        return selectableComboBox;
    }

    private void addListener(SelectableComboBox selectableComboBox) {
        ComboBoxState comboBoxState = new ComboBoxState(selectableComboBox);
        jComboBox.addActionListener(comboBoxState);
    }

    private void setLocation(SelectableComboBox selectableComboBox) {
        int locationX = selectableComboBox.getLocationX();
        int locationY = selectableComboBox.getLocationY();
        jComboBox.setLocation(locationX, locationY);
    }

}
