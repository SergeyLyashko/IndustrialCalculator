package view.calculator.menuboxes;

import view.AppComponent;
import view.ReceivableMenu;
import view.Visitor;
import view.calculator.SelectableMenu;

import javax.swing.*;
import java.util.List;

public class TypesMenu implements SelectableMenu {

    private final JComboBox<String> jComboBox;

    private static final String TYPE_HEADER = "Тип профиля";
    private static final String THEME_TOOL_TIP_TEXT = "выбор типа профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 60;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private ReceivableMenu receivableMenu;

    public TypesMenu(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
    }

    @Override
    public void addReceiver(ReceivableMenu receivableMenu) {
        this.receivableMenu = receivableMenu;
    }

    @Override
    public List<String> receiveMenu(String menuItem) {
        return receivableMenu.getTypeMenu(menuItem);
    }

    @Override
    public String getHeaderMenu() {
        return TYPE_HEADER;
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
        MenuBehavior menuItemBehavior = new MenuBehavior(selectableMenu);
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
