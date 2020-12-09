package view.view.calculator;

import view.DataBaseMenuReceiver;
import view.controller.ViewController;
import view.view.MenuSelectable;
import view.view.AppComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class AssortmentsMenu implements MenuSelectable, Comparable<AppComponent> {

    private final JComboBox<String> jComboBox;

    private static final int FOCUSED_RATE = 1;
    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TOOL_TIP_TEXT = "выбор сортамента детали";
    private static final String DEFAULT_MENU_VALUE = "";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;
    private final DataBaseMenuReceiver dataBaseMenuReceiver;

    AssortmentsMenu(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver){
        this.viewController = viewController;
        this.dataBaseMenuReceiver = dataBaseMenuReceiver;

        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);

        viewController.setAllFieldOffState();
        addListener(viewController);
    }

    private void addListener(ViewController viewController){
        jComboBox.addActionListener(event -> viewController.setAllFieldOffState());
    }

    @Override
    public void createMenu(String...menuItem) {
        List<String> assortmentMenu = dataBaseMenuReceiver.getAssortmentMenu();
        createMenu(assortmentMenu);
    }

    private void createMenu(List<String> receiveMenu){
        List<String> menu = new ArrayList<>();
        menu.add(ASSORTMENT_HEADER);
        menu.addAll(receiveMenu);
        viewController.createMenu(menu, this);
    }

    @Override
    public void addListenerMenu(MenuSelectable child){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            child.createMenu(selectedItem, DEFAULT_MENU_VALUE);
        });
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
    public int compareTo(AppComponent o) {
        return this.getFocusedRate() - o.getFocusedRate();
    }
}
