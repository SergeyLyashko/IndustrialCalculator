package view.view.calculator;

import view.MenuListReceiver;
import view.controller.ViewController;
import view.controller.MenuSelectable;
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
    private final MenuListReceiver menuListReceiver;
    private String assortment = DEFAULT_MENU_VALUE;
    private String type = DEFAULT_MENU_VALUE;

    NumbersMenu(ViewController viewController, MenuListReceiver menuListReceiver){
        this.viewController = viewController;
        this.menuListReceiver = menuListReceiver;

        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);

        jComboBox.addActionListener(event -> {
            viewController.actionState();
        });
    }

    @Override
    public void receiveMenu(String...menuItem) {
        if(menuItem.length != 0) {
            assortment = menuItem[0];
        }
        if(menuItem.length > 0){
            type = menuItem[1];
        }
        List<String> menu = new ArrayList<>();
        menu.add(NUMBER_HEADER);
        List<String> numberMenu = menuListReceiver.getNumberMenu(assortment, type);
        menu.addAll(numberMenu);
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
