package view.calculator.menuboxes;

import view.AppComponent;
import view.MenuReceivable;
import view.calculator.MenuSelectable;
import view.calculator.fields.FieldSelectable;

import javax.swing.*;
import java.util.List;

public class NumbersMenu implements MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String NUMBER_HEADER = "№ профиля";
    private static final String TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 100;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private MenuReceivable menuReceivable;

    public NumbersMenu(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
    }

    @Override
    public void addReceiver(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
    }

    @Override
    public List<String> receiveMenu(String menuItem) {
        return menuReceivable.getNumberMenu(menuItem);
    }

    @Override
    public String getHeaderMenu() {
        return NUMBER_HEADER;
    }

    @Override
    public void setMenuModel(MenuModel menuModel) {
        jComboBox.setModel(menuModel);
    }

    @Override
    public <T extends AppComponent> void addListener(T componentListener) {
        if(componentListener instanceof FieldSelectable){
            jComboBox.addActionListener(e -> ((FieldSelectable) componentListener).activate());
        }
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
