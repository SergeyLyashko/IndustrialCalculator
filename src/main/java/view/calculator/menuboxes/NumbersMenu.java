package view.calculator.menuboxes;

import view.AppComponent;
import view.MenuReceiver;
import view.Visitor;
import view.calculator.MenuSelectable;

import javax.swing.*;
import java.util.List;

public class NumbersMenu implements MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String NUMBER_HEADER = "№ профиля";
    private static final String THEME_TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private MenuReceiver menuReceiver;

    public NumbersMenu(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
    }

    @Override
    public void addReceiver(MenuReceiver menuReceiver) {
        this.menuReceiver = menuReceiver;
    }

    @Override
    public List<String> receiveMenu(String menuItem) {
        return menuReceiver.getNumberMenu(menuItem);
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
    public void addListener(MenuSelectable menuSelectable) {
        MenuBehavior menuItemBehavior = new MenuBehavior(menuSelectable);
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
