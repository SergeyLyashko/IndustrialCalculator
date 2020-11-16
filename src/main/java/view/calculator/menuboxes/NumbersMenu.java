package view.calculator.menuboxes;

import view.AppComponent;
import view.MenuReceiver;
import view.Visitor;
import view.calculator.MenuSelectable;

import javax.swing.*;
import java.util.List;

public class NumbersMenu implements AppComponent, MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String NUMBER_HEADER = "№ профиля";
    private static final String THEME_TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;

    public NumbersMenu(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
    }

    @Override
    public String getCurrentMenu() {
        return (String) jComboBox.getSelectedItem();
    }

    @Override
    public void actionMenu(String currentMenu) {
        System.out.println("numbers select: "+currentMenu);
    }

    @Override
    public List<String> receiveMenu(MenuReceiver menuReceiver) {
        //menuReceiver.getNumberMenu();
        return null;
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
    public AppComponent getMenu() {
        return this;
    }

    @Override
    public void addListener(Visitor visitor) {
        MenuBehavior menuItemBehavior = new MenuBehavior(this);
        jComboBox.addActionListener(menuItemBehavior);
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
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}
