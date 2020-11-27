package view.view.calculator;

import view.controller.MenuReceivable;
import view.controller.ViewController;
import view.controller.MenuSelectable;

import javax.swing.*;
import java.util.List;

class NumbersMenu implements MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String NUMBER_HEADER = "№ профиля";
    private static final String TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;

    NumbersMenu(ViewController viewController){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);

        this.viewController = viewController;
        jComboBox.addActionListener(event -> {
            viewController.actionState();
        });
    }

    @Override
    public List<String> receiveMenu(MenuReceivable menuReceivable, String menuItem) {
        return menuReceivable.getNumberMenu(menuItem);
    }

    @Override
    public String getHeaderMenu() {
        return NUMBER_HEADER;
    }

    @Override
    public int getLocationX() {
        return LOCATION_X;
    }

    @Override
    public int getLocationY() {
        return LOCATION_Y;
    }

    @Override
    public JComponent getParent() {
        return jComboBox;
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}
