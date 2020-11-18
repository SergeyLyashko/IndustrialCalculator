package view.calculator.menuboxes;

import view.AppComponent;
import view.ReceivableMenu;
import view.calculator.SelectableMenu;

import javax.swing.*;
import java.util.List;

public class NumbersMenu implements SelectableMenu {

    private final JComboBox<String> jComboBox;

    private static final String NUMBER_HEADER = "№ профиля";
    private static final String THEME_TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private ReceivableMenu receivableMenu;

    public NumbersMenu(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
    }

    @Override
    public void addReceiver(ReceivableMenu receivableMenu) {
        this.receivableMenu = receivableMenu;
    }

    @Override
    public void setSelected(String selectedItem) {
        System.out.println("number set selected: "+selectedItem);
    }

    @Override
    public List<String> receiveMenu(String...menuItem) {
        //String assortment = menuItem[0];
        String type = menuItem[0];
        return receivableMenu.getNumberMenu(type);
    }

    @Override
    public String getHeaderMenu() {
        return NUMBER_HEADER;
    }

    @Override
    public void setModel(MenuModel menuModel) {
        jComboBox.setModel(menuModel);
    }

    @Override
    public AppComponent getComponent() {
        return this;
    }

    @Override
    public void addListener(SelectableMenu selectableMenu) {
        System.out.println("add numbers listener: "+selectableMenu.getHeaderMenu());
        // TODO должен быть слушатель поле ввода!
        //MenuBehavior menuItemBehavior = new MenuBehavior(selectableMenu);
        //jComboBox.addActionListener(menuItemBehavior);
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

}
