package view.view.calculator;

import view.controller.MenuReceivable;
import view.controller.ViewController;
import view.model.CalculatorFieldState;
import view.controller.MenuSelectable;
import view.controller.FieldState;

import javax.swing.*;
import java.util.List;

class TypesMenu implements MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String TYPE_HEADER = "Тип профиля";
    private static final String TOOL_TIP_TEXT = "выбор типа профиля детали";
    private static final int LOCATION_X = 20;
    private static final int LOCATION_Y = 60;
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private final ViewController viewController;

    TypesMenu(ViewController viewController){
        jComboBox = new JComboBox<>();
        jComboBox.setSize(WIDTH, HEIGHT);
        jComboBox.setSelectedIndex(-1);
        jComboBox.setToolTipText(TOOL_TIP_TEXT);
        this.viewController = viewController;
    }

    @Override
    public void addFieldStateListener(CalculatorFieldState calculatorFieldState) {
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            if(selectedItem.equalsIgnoreCase("резиновая пластина") ||
                selectedItem.equalsIgnoreCase("тонколистовая") ||
                selectedItem.equalsIgnoreCase("толстолистовая") ||
                selectedItem.equalsIgnoreCase("рифленая(ромб)")){
                    FieldState haveWidthState = calculatorFieldState.getHaveWidthState();
                    calculatorFieldState.setState(haveWidthState);
                    calculatorFieldState.selectMenu();
            } else{
                FieldState notWidthState = calculatorFieldState.getNotWidthState();
                calculatorFieldState.setState(notWidthState);
                calculatorFieldState.selectMenu();
            }
        });
    }

    @Override
    public List<String> receiveMenu(MenuReceivable menuReceivable, String menuItem) {
        return menuReceivable.getTypeMenu(menuItem);
    }

    @Override
    public String getHeaderMenu() {
        return TYPE_HEADER;
    }

    @Override
    public void addMenuListener(MenuSelectable menuListener){
        jComboBox.addActionListener(event -> {
            String selectedItem = (String) jComboBox.getSelectedItem();
            viewController.selectMenu(menuListener, selectedItem);
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

    @Override
    public boolean isFocused() {
        return true;
    }
 }
