package view.view.calculator;

import view.DataBaseMenuReceiver;
import view.controller.ViewController;
import view.view.MenuSelectable;
import view.view.AppComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class NumbersMenu implements MenuSelectable, Comparable<AppComponent> {

    private final JComboBox<String> jComboBox;

    private static final int FOCUSED_RATE = 3;
    private static final String NUMBER_HEADER = "№ профиля";
    private static final String TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final String DEFAULT_MENU_VALUE = "";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;
    private final DataBaseMenuReceiver dataBaseMenuReceiver;
    private String assortment = DEFAULT_MENU_VALUE;
    private String type = DEFAULT_MENU_VALUE;

    NumbersMenu(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver){
        this.viewController = viewController;
        this.dataBaseMenuReceiver = dataBaseMenuReceiver;
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        addListener(viewController);
    }

    private void addListener(ViewController viewController){
        jComboBox.addActionListener(event -> {
            viewController.actionState();
            String selectedItem = (String) jComboBox.getSelectedItem();
            if(!selectedItem.equals(NUMBER_HEADER)){
                viewController.setParameters(assortment, type, selectedItem);
            }
        });
    }

    @Override
    public void createMenu(String...menuItem) {
        if(menuItem.length != 0) {
            assortment = menuItem[0];
        }
        if(menuItem.length > 0){
            type = menuItem[1];
        }
        List<String> numberMenu = dataBaseMenuReceiver.getNumberMenu(assortment, type);
        createMenu(numberMenu);
    }

    private void createMenu(List<String> receiveMenu){
        List<String> menu = new ArrayList<>();
        menu.add(NUMBER_HEADER);
        menu.addAll(receiveMenu);
        viewController.createMenu(menu, this);
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
    public JComboBox<String> getParent() {
        return jComboBox;
    }

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

    @Override
    public int getFocusedRate() {
        return FOCUSED_RATE;
    }

    @Override
    public int compareTo(AppComponent o) {
        return this.getFocusedRate() - o.getFocusedRate();
    }
}
