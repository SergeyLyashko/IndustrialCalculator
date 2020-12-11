package view.view.calculator;

import view.DataBaseMenuReceiver;
import view.controller.ViewController;
import view.view.MenuSelectable;
import view.view.AppComponent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private final DataBaseMenuReceiver dataBaseMenuReceiver;
    private String assortment = DEFAULT_MENU_VALUE;
    private boolean connect = true;

    TypesMenu(ViewController viewController, DataBaseMenuReceiver dataBaseMenuReceiver){
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
            viewController.setAllFieldOffState();
            if(selectedItem.equalsIgnoreCase("резиновая пластина") ||
                    selectedItem.equalsIgnoreCase("тонколистовая") ||
                    selectedItem.equalsIgnoreCase("толстолистовая") ||
                    selectedItem.equalsIgnoreCase("рифленая(ромб)")){
                viewController.setWidthOnState();
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
        List<String> typeMenu = null;
        try {
            if(menuItem.length != 0){
                assortment = menuItem[0];
            }
            typeMenu = dataBaseMenuReceiver.getTypeMenu(assortment);
        } catch (SQLException exception) {
            connect = false;
        }
        createMenu(typeMenu);
    }

    private void createMenu(List<String> receiveMenu){
        List<String> menu = new ArrayList<>();
        menu.add(TYPE_HEADER);
        if(receiveMenu != null) {
            menu.addAll(receiveMenu);
        }
        viewController.createMenu(menu, this);
    }

    @Override
    public void addListenerMenu(MenuSelectable child){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            child.receiveMenu(assortment, selectedItem);
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
