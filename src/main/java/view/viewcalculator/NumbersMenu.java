package view.viewcalculator;

import view.DataBaseMenuReceiver;
import view.viewcontroller.ViewController;
import view.MenuSelectable;
import view.AppComponent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class NumbersMenu implements MenuSelectable, Comparable<AppComponent> {

    private final JComboBox<String> jComboBox;

    private static final int FOCUSED_RATE = 3;
    private static final String NUMBER_HEADER = "№ профиля";
    private static final String TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;
    private final DataBaseMenuReceiver dataBaseMenuReceiver;
    private String assortment = DEFAULT_MENU_VALUE;
    private String type = DEFAULT_MENU_VALUE;
    private boolean connect = true;

    NumbersMenu(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver){
        this.viewController = viewController;
        this.dataBaseMenuReceiver = dataBaseMenuReceiver;
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        addListener(viewController);
        clickListener();
    }

    private void addListener(ViewController viewController){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            if(!selectedItem.equals(NUMBER_HEADER)){
                viewController.setParameters(assortment, type, selectedItem);
            }
            if(connect){
                viewController.actionState();
            }
        });
    }

    private void clickListener(){
        jComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if(!connect){
                    viewController.setMessage(NOT_DATABASE_MESSAGE, true);
                    viewController.setResult(ERROR, true);
                }
            }
        });
    }

    @Override
    public void receiveMenu(String...menuItem) {
        List<String> numberMenu = null;
        try {
            if(menuItem.length != 0) {
                assortment = menuItem[0];
            }
            if(menuItem.length > 1){
                type = menuItem[1];
            }
            numberMenu = dataBaseMenuReceiver.getNumberMenu(assortment, type);
        } catch (SQLException exception) {
            connect = false;
        }
        createMenu(numberMenu);
    }

    private void createMenu(List<String> receiveMenu){
        List<String> menu = new ArrayList<>();
        menu.add(NUMBER_HEADER);
        if(receiveMenu != null) {
            menu.addAll(receiveMenu);
        }
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
