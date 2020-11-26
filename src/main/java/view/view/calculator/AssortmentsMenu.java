package view.view.calculator;

import view.controller.MenuReceivable;
import view.controller.ViewController;
import view.controller.MenuSelectable;

import javax.swing.*;
import java.util.List;

class AssortmentsMenu implements MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TOOL_TIP_TEXT = "выбор сортамента детали";

    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;

    AssortmentsMenu(ViewController viewController){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        this.viewController = viewController;
        jComboBox.addActionListener(event -> {
            viewController.setNotWidthState(this);
        });
    }

    @Override
    public List<String> receiveMenu(MenuReceivable menuReceivable, String menuItem) {
        return menuReceivable.getAssortmentMenu();
    }

    @Override
    public String getHeaderMenu() {
        return ASSORTMENT_HEADER;
    }

    @Override
    public void addMenuListener(MenuSelectable menuListener){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            viewController.selectMenu(menuListener, selectedItem);
        });
    }

    @Override
    public boolean isFocused() {
        return true;
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
}
