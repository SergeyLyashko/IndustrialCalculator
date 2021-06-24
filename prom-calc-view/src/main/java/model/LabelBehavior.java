package model;

import javax.swing.*;

public interface LabelBehavior {

    void reset();

    void show(String value, boolean alert);

    void setComponent(JComponent component);
}
