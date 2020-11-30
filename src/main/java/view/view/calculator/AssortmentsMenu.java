package view.view.calculator;

import view.MenuListReceiver;
import view.controller.ViewController;
import view.controller.MenuSelectable;
import view.view.AppComponent;

import javax.swing.*;
import java.util.List;

class AssortmentsMenu implements MenuSelectable, Comparable<AppComponent> {

    private final JComboBox<String> jComboBox;

    private static final int FOCUSED_RATE = 1;
    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TOOL_TIP_TEXT = "выбор сортамента детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;

    AssortmentsMenu(ViewController viewController){
        this.viewController = viewController;
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);

        viewController.setAllFieldOffState();
        addListener(viewController);
    }

    private void addListener(ViewController viewController){
        jComboBox.addActionListener(event -> {
            viewController.setAllFieldOffState();
        });
    }

    @Override
    public List<String> receiveMenu(String menuItem) {
        MenuListReceiver menuReceiver = viewController.getMenuReceiver();
        return menuReceiver.getAssortmentMenu();
    }

    @Override
    public String getHeaderMenu() {
        return ASSORTMENT_HEADER;
    }

    @Override
    public void addChildMenu(MenuSelectable child){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            viewController.selectMenu(child, selectedItem);
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
    public JComponent getParent() {
        return jComboBox;
    }

    @Override
    public int compareTo(AppComponent o) {
        return this.getFocusedRate() - o.getFocusedRate();
    }
}
