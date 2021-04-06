package viewcomponents.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.DataReceiver;
import view.ViewController;
import view.MenuSelectable;
import view.AppComponent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("assortmentsMenu")
class AssortmentsMenu implements MenuSelectable, Comparable<AppComponent> {

    private final JComboBox<String> jComboBox;
    private static final int FOCUSED_RATE = 1;
    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TOOL_TIP_TEXT = "выбор сортамента детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private ViewController viewController;
    private DataReceiver dataReceiver;
    private boolean connect = true;

    @Autowired
    public void setDataReceiver(DataReceiver dataReceiver){
        this.dataReceiver = dataReceiver;
    }

    @Autowired
    public void setViewController(ViewController viewController){
        this.viewController = viewController;
    }

    AssortmentsMenu(/*ViewController controller, DataReceiver dataReceiver*/){
        //this.viewController = viewController;
        //this.dataReceiver = dataReceiver;
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        addListener(viewController);
        clickListener();
        receiveMenu();
    }

    private void addListener(ViewController viewController){
        jComboBox.addActionListener(event -> viewController.fieldsOff());
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
        List<String> assortmentMenu = null;
        try {
            assortmentMenu = dataReceiver.createAssortmentMenu();
        } catch (SQLException exception) {
            connect = false;
        }
        createMenu(assortmentMenu);
    }

    private void createMenu(List<String> receiveMenu){
        List<String> menu = new ArrayList<>();
        menu.add(ASSORTMENT_HEADER);
        if(receiveMenu != null) {
            menu.addAll(receiveMenu);
        }
        viewController.createMenu(menu, this);
    }

    @Override
    public void addMenuSelectListener(MenuSelectable listener){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            listener.receiveMenu(selectedItem, DEFAULT_MENU_VALUE);
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
