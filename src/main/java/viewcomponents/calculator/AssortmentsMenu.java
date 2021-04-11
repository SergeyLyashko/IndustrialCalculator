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
import java.util.LinkedList;
import java.util.List;

@Component
class AssortmentsMenu implements MenuSelectable, Comparable<AppComponent> {

    private final JComboBox<String> jComboBox;
    private static final int FOCUSED_RATE = 1;
    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TOOL_TIP_TEXT = "выбор сортамента детали";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private ViewController viewController;
    private DataReceiver dataReceiver;
    private boolean isConnect = true;

    public AssortmentsMenu(int locationX, int locationY){
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
    private void afterPropertiesSet() {
        addListener(viewController);
        clickListener();
        List<String> receivableMenu = receiveMenu();
        createMenuModel(receivableMenu);
    }

    private void addListener(ViewController viewController){
        jComboBox.addActionListener(event -> viewController.fieldsOff());
    }

    private void clickListener(){
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
    public void setMenuItems(String...menuItem) {
        List<String> receivableMenu = receiveMenu();
        createMenuModel(receivableMenu);
    }

    private List<String> receiveMenu() {
        try {
            return dataReceiver.receiveAssortmentMenu();
        } catch (SQLException exception) {
            isConnect = false;
        }
        return null;
    }

    private void createMenuModel(List<String> receivableMenu){
        if(receivableMenu == null){
            receivableMenu = new LinkedList<>();
        }
        receivableMenu.add(0, ASSORTMENT_HEADER);
        viewController.createMenu(receivableMenu, this);
    }

    @Override
    public void addMenuSelectListener(MenuSelectable listener){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            listener.setMenuItems(selectedItem, DEFAULT_MENU_VALUE);
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
    public JComboBox<String> getComponentParent() {
        return jComboBox;
    }

    @Override
    public int compareTo(AppComponent o) {
        return this.getFocusedRate() - o.getFocusedRate();
    }
}
