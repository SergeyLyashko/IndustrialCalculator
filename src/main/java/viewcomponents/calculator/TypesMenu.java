package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.DataReceiver;
import view.ViewController;
import view.MenuSelectable;
import view.AppComponent;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component("typesMenu")
public class TypesMenu implements MenuSelectable, Comparable<AppComponent> {

    private static final int FOCUSED_RATE = 2;
    private static final String TYPE_HEADER = "Тип профиля";
    private static final String TOOL_TIP_TEXT = "выбор типа профиля детали";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;

    private String assortment = DEFAULT_MENU_VALUE;
    private final JComboBox<String> jComboBox;
    private ViewController viewController;
    private DataReceiver dataReceiver;
    private boolean isConnect = true;

    public TypesMenu(int locationX, int locationY){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        jComboBox.setLocation(locationX, locationY);
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
    private void afterPropertiesSet() throws Exception {
        addActionListener();
        addClickListener();
        List<String> receivableMenu = receiveMenu();
        createMenuModel(receivableMenu);
    }

    private List<String> receiveMenu() {
        try {
            return dataReceiver.receiveTypeMenu(assortment);
        } catch (SQLException exception) {
            isConnect = false;
        }
        return null;
    }

    @Override
    public void setMenuItems(String...menuItem) {
        if(menuItem.length != 0){
            assortment = menuItem[0];
        }
        List<String> receivableMenuList = receiveMenu();
        createMenuModel(receivableMenuList);
    }

    private void createMenuModel(List<String> receivableMenu){
        if(receivableMenu == null){
            receivableMenu = new LinkedList<>();
        }
        receivableMenu.add(0, TYPE_HEADER);
        viewController.createMenu(receivableMenu, this);
    }

    private void addActionListener(){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            viewController.fieldsOff();
            if(selectedItem.equalsIgnoreCase("резиновая пластина") ||
                    selectedItem.equalsIgnoreCase("тонколистовая") ||
                    selectedItem.equalsIgnoreCase("толстолистовая") ||
                    selectedItem.equalsIgnoreCase("рифленая(ромб)")){
                viewController.widthOn();
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

    @Override
    public void addMenuSelectListener(MenuSelectable listener){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            listener.setMenuItems(assortment, selectedItem);
        });
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
