package view.view.calculator;

import view.MenuListReceiver;
import view.controller.ViewController;
import view.controller.MenuSelectable;
import view.view.AppComponent;

import javax.swing.*;
import java.util.List;

class TypesMenu implements MenuSelectable, Comparable<AppComponent> {

    private final JComboBox<String> jComboBox;

    private static final int FOCUSED_RATE = 2;
    private static final String TYPE_HEADER = "Тип профиля";
    private static final String TOOL_TIP_TEXT = "выбор типа профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 60;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;

    TypesMenu(ViewController viewController){
        this.viewController = viewController;
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);

        addListener(viewController);
    }

    private void addListener(ViewController viewController){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            viewController.setAllFieldOffState();
            if(selectedItem.equalsIgnoreCase("резиновая пластина") ||
                    selectedItem.equalsIgnoreCase("тонколистовая") ||
                    selectedItem.equalsIgnoreCase("толстолистовая") ||
                    selectedItem.equalsIgnoreCase("рифленая(ромб)")){
                viewController.setWidthState();
            } else{
                viewController.setNotWidthState();
            }
        });
    }

    @Override
    public List<String> receiveMenu(String menuItem) {
        MenuListReceiver menuReceiver = viewController.getMenuReceiver();
        return menuReceiver.getTypeMenu(menuItem);
    }

    @Override
    public String getHeaderMenu() {
        return TYPE_HEADER;
    }

    @Override
    public void addChildMenu(MenuSelectable child){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            viewController.selectMenu(child, selectedItem);
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
