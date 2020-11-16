package view.calculator.menuboxes;

import view.AppComponent;
import view.MenuReceiver;
import view.Visitor;
import view.calculator.MenuSelectable;

import javax.swing.*;
import java.util.List;

public class AssortmentsMenu implements AppComponent, MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String THEME_TOOL_TIP_TEXT = "выбор сортамента детали";

    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;

    public AssortmentsMenu(){
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
        System.out.println("assortment select: "+currentMenu);
    }

    @Override
    public List<String> receiveMenu(MenuReceiver menuReceiver) {
        return menuReceiver.getAssortmentMenu();
    }

    @Override
    public String getHeaderMenu() {
        return ASSORTMENT_HEADER;
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
