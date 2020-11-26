package view.view.calculator;

import view.controller.MenuReceivable;
import view.controller.ViewController;
import view.controller.MenuSelectable;

import javax.swing.*;
import java.util.List;

class TypesMenu implements MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String TYPE_HEADER = "Тип профиля";
    private static final String TOOL_TIP_TEXT = "выбор типа профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 60;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;

    TypesMenu(ViewController viewController){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        this.viewController = viewController;

        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            if(selectedItem.equalsIgnoreCase("резиновая пластина") ||
                    selectedItem.equalsIgnoreCase("тонколистовая") ||
                    selectedItem.equalsIgnoreCase("толстолистовая") ||
                    selectedItem.equalsIgnoreCase("рифленая(ромб)")){
                viewController.setWidthState(this);
            } else{
                viewController.setNotWidthState(this);
            }
        });
    }

    @Override
    public List<String> receiveMenu(MenuReceivable menuReceivable, String menuItem) {
        return menuReceivable.getTypeMenu(menuItem);
    }

    @Override
    public String getHeaderMenu() {
        return TYPE_HEADER;
    }

    @Override
    public void addMenuListener(MenuSelectable menuListener){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            viewController.selectMenu(menuListener, selectedItem);
        });
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
