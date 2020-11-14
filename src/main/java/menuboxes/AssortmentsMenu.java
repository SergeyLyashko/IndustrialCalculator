package menuboxes;

import appcomponents.SwingComponent;
import appcomponents.Visitor;

import javax.swing.*;

public class AssortmentsMenu implements SwingComponent, MenuSelectable {

    private final JComboBox<String> jComboBox;

    private static final String BOX_NAME = "сортамент";
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
        System.out.println("assortment select");
        return null;
    }

    @Override
    public void actionMenu(String currentMenu) {

    }

    @Override
    public void addListener(SwingComponent component, Visitor visitor) {
        MenuBehavior menuItemBehavior = new MenuBehavior();
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
