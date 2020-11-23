package view.calculator.menuboxes;

import view.AppComponent;
import view.MenuReceivable;
import view.calculator.CalculatorFieldState;
import view.calculator.MenuSelectable;
import view.calculator.fields.FieldSelectable;
import view.calculator.state.FieldState;

import javax.swing.*;
import java.util.List;

public class AssortmentsMenu implements MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TOOL_TIP_TEXT = "выбор сортамента детали";

    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 20;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private MenuReceivable menuReceivable;

    public AssortmentsMenu(){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
    }

    @Override
    public List<String> receiveMenu(String menuItem) {
        return menuReceivable.getAssortmentMenu();
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
        if(componentListener instanceof MenuSelectable){
            addMenuListener((MenuSelectable) componentListener);
        }
        if(componentListener instanceof FieldSelectable){
            addFieldListener((FieldSelectable) componentListener);
        }
    }

    private void addMenuListener(MenuSelectable menuSelectable){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            MenuModel menuModel = new MenuModel(menuSelectable, selectedItem);
            menuSelectable.setMenuModel(menuModel);
        });
    }

    private void addFieldListener(FieldSelectable fieldSelectable){
        jComboBox.addActionListener(event -> {
            fieldSelectable.deactivate();
        });
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public void addReceiver(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
    }

    @Override
    public void addListener(CalculatorFieldState fieldState) {
        jComboBox.addActionListener(event -> {
            fieldState.setState(fieldState.getWidthFieldOffState());
            fieldState.selectMenu();
        });
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
