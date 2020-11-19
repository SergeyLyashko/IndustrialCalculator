package view.calculator.menuboxes;

import view.AppComponent;
import view.ReceivableMenu;
import view.calculator.SelectableMenu;
import view.calculator.fields.FieldSelectable;

import javax.swing.*;
import java.util.List;

public class AssortmentsMenu implements SelectableMenu {

    private final JComboBox<String> jComboBox;

    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String THEME_TOOL_TIP_TEXT = "выбор сортамента детали";

    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private ReceivableMenu receivableMenu;

    public AssortmentsMenu(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
    }

    @Override
    public List<String> receiveMenu(String menuItem) {
        return receivableMenu.getAssortmentMenu();
    }

    @Override
    public String getHeaderMenu() {
        return ASSORTMENT_HEADER;
    }

    @Override
    public void setMenuModel(MenuModel menuModel) {
        jComboBox.setModel(menuModel);
    }

    @Override
    public <T extends AppComponent> void addListener(T componentListener) {
        if(componentListener instanceof SelectableMenu){
            SelectableMenuBehavior menuItemBehavior = new SelectableMenuBehavior((SelectableMenu) componentListener);
            jComboBox.addActionListener(menuItemBehavior);
        }
        if(componentListener instanceof FieldSelectable){
            jComboBox.addActionListener(e -> ((FieldSelectable) componentListener).deactivate());
        }
    }

    @Override
    public void addReceiver(ReceivableMenu receivableMenu) {
        this.receivableMenu = receivableMenu;
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
