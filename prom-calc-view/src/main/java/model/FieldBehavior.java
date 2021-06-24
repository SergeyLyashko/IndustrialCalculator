package model;

import javax.swing.*;

public interface FieldBehavior {

    void fieldDeactivate(JComponent component);

    void fieldActivate(JComponent component);

    void areaActivate(JComponent component);

    void areaDeactivate(JComponent component);

}
