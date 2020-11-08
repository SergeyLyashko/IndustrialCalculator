package comboboxes;

import javax.swing.*;
import java.awt.*;

public class NumbersMenu implements SelectableComboBox {

    private static final String BOX_NAME = "номер";
    private static final String THEME_TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private JComponent componentSwing;

    @Override
    public int getLocationX() {
        return LOCATION_X;
    }

    @Override
    public int getLocationY() {
        return LOCATION_Y;
    }

    @Override
    public String getName() {
        return BOX_NAME;
    }

    @Override
    public Container getParent() {
        return componentSwing;
    }

    @Override
    public void setParent(JComponent jComponent) {
        this.componentSwing = jComponent;
    }
}
