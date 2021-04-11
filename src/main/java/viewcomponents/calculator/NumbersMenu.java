package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import viewcomponents.common.DataReceiver;
import viewcomponents.common.ViewController;
import viewcomponents.common.MenuSelectable;
import viewcomponents.common.AppComponent;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.*;

@Component("numbersMenu")
public class NumbersMenu implements MenuSelectable, Comparable<AppComponent> {

    private static final int FOCUSED_RATE = 3;
    private static final String NUMBER_HEADER = "№ профиля";
    private static final String TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;

    private final JComboBox<String> jComboBox;
    private final HashMap<String, String> data;
    private ViewController viewController;
    private DataReceiver dataReceiver;
    private String assortment = DEFAULT_MENU_VALUE;
    private String type = DEFAULT_MENU_VALUE;
    private boolean isConnect = true;

    public NumbersMenu(int locationX, int locationY){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        jComboBox.setLocation(locationX, locationY);
        this.data = new HashMap<>(3);
        data.put("assortment", assortment);
        data.put("type", type);
        data.put("number", DEFAULT_MENU_VALUE);
    }

    @Autowired
    public void setDataReceiver(DataReceiver dataReceiver){
        this.dataReceiver = dataReceiver;
    }

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    @PostConstruct
    private void afterPropertiesSet() {
        addActionListener();
        addClickListener();
        List<String> receivableMenu = receiveMenuList();
        createMenuModel(receivableMenu);
    }

    private void createMenuModel(List<String> receivableMenu){
        if(receivableMenu == null){
            receivableMenu = new LinkedList<>();
        }
        receivableMenu.add(0, NUMBER_HEADER);
        viewController.createMenu(receivableMenu, this);
    }

    private List<String> receiveMenuList() {
        try {
            return dataReceiver.receiveNumberMenu(assortment, type);
        } catch (SQLException exception) {
            isConnect = false;
        }
        return null;
    }

    @Override
    public void setMenuItems(String...menuItem) {
        if(menuItem.length != 0) {
            assortment = menuItem[0];
        }
        if(menuItem.length > 1){
            type = menuItem[1];
        }
        List<String> receivableMenuList = receiveMenuList();
        createMenuModel(receivableMenuList);
    }

    private void addActionListener(){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            if(selectedItem != null && !selectedItem.equals(NUMBER_HEADER)){
                Map<String, String> selectedItems = collectSelectedMenuItems(selectedItem);
                viewController.addSelectedItems(selectedItems);
            }
            if(isConnect){
                viewController.action();
            }
        });
    }

    private void addClickListener(){
        jComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if(!isConnect){
                    viewController.setMessage(NOT_DATABASE_MESSAGE, true);
                    viewController.setResult(ERROR, true);
                }
            }
        });
    }

    private Map<String, String> collectSelectedMenuItems(String number){
        data.replace("assortment", assortment);
        data.replace("type", type);
        data.replace("number", number);
        return data;
    }

    @Override
    public JComboBox<String> getComponentParent() {
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
