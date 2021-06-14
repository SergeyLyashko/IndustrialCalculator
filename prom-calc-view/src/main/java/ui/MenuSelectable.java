package ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public interface MenuSelectable extends UiComponent {

    JComboBox<String> getComponent();

    String getSelectedItem();

    void addActionListener(ActionListener actionListener);

    void addMouseListener(MouseListener mouseListener);

}
