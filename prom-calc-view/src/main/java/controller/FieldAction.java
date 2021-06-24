package controller;

import javax.swing.*;

public interface FieldAction {

    void setComponent(JComponent component);

    void deactivate();

    void activate();

    void areaActivate();

    void areaDeactivate();
}
