package ui.calculator;

import controller.ViewController;
import database.DataReceiver;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.MenuSelectable;
import ui.UiComponent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope("prototype")
public class MenuBox extends JComboBox<String> implements MenuSelectable, Comparable<UiComponent> {

    private static final String DEFAULT_MENU_VALUE = "";
    private static final String NOT_DATABASE_MESSAGE = "Отсутствует соединение с БД";
    private static final String ERROR = "error";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    @Getter
    private final int focusRate;
    private static boolean isConnect = true;

    public MenuBox(String toolTipText, int locationX, int locationY, int focusRate){
        super.setSize(WIDTH, HEIGHT);
        super.setSelectedIndex(-1);
        super.setToolTipText(toolTipText);
        super.setLocation(locationX, locationY);
        this.focusRate = focusRate;
    }

    @Override
    public JComboBox<String> getComponentParent() {
        return this;
    }

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

    @Override
    public int compareTo(UiComponent component) {
        return this.getFocusRate() - component.getFocusRate();
    }

    enum ProfileType {

        ASSORTMENTS{
            @Override
            void receiveMenu(MenuBox menuBox, DataReceiver dataReceiver, ViewController viewController) {
                String assortmentHeader = "Тип сортамента";
                List<String> receivableMenu = null;
                try {
                    receivableMenu = dataReceiver.receiveAssortmentMenu();
                } catch (SQLException exception) {
                    isConnect = false;
                }
                ProfileType.createMenuModel(menuBox, assortmentHeader, viewController, receivableMenu);
            }

            @Override
            void addActionListener(MenuBox menuBox, ViewController viewController) {
                menuBox.addActionListener(event -> {
                    viewController.fieldsOff();
                    String selectedAssortment = (String) menuBox.getSelectedItem();
                    data.replace("assortment", selectedAssortment);
                });
                ProfileType.clickListener(menuBox, viewController);
            }
        },

        TYPES{
            @Override
            void receiveMenu(MenuBox menuBox, DataReceiver dataReceiver, ViewController viewController) {
                String typeHeader = "Тип профиля";
                //String assortment = menuItem.length != 0 ? menuItem[0] : DEFAULT_MENU_VALUE;
                List<String> receivableMenu = null;
                try {
                    receivableMenu = dataReceiver.receiveTypeMenu(data.get("assortment"));
                } catch (SQLException exception) {
                    isConnect = false;
                }
                createMenuModel(menuBox, typeHeader, viewController, receivableMenu);
            }

            @Override
            void addActionListener(MenuBox menuBox, ViewController viewController) {
                menuBox.addActionListener(event -> {
                    String selectedType = (String) menuBox.getSelectedItem();
                    viewController.fieldsOff();
                    if(selectedType.equalsIgnoreCase("резиновая пластина") ||
                            selectedType.equalsIgnoreCase("тонколистовая") ||
                            selectedType.equalsIgnoreCase("толстолистовая") ||
                            selectedType.equalsIgnoreCase("рифленая(ромб)")){
                        viewController.widthOn();
                    }
                    //listener.setMenuItems(data.get("assortment"), selectedType);
                    data.replace("type", selectedType);
                });
                ProfileType.clickListener(menuBox, viewController);
            }
        },

        NUMBERS{
            @Override
            void receiveMenu(MenuBox menuBox, DataReceiver dataReceiver, ViewController viewController) {
                String numberHeader = "№ профиля";
                List<String> receivableMenu = null;
                //String assortment = menuItem.length != 0 ? menuItem[0] : DEFAULT_MENU_VALUE;
                //String type = menuItem.length > 1 ? menuItem[1] : DEFAULT_MENU_VALUE;
                try {
                    receivableMenu = dataReceiver.receiveNumberMenu(data.get("assortment"), data.get("type"));
                } catch (SQLException exception) {
                    isConnect = false;
                }
                //data.put("assortment", assortment);
                //data.put("type", type);
                createMenuModel(menuBox, numberHeader, viewController, receivableMenu);
            }

            @Override
            void addActionListener(MenuBox menuBox, ViewController viewController) {
                String numberHeader = "№ профиля";
                menuBox.addActionListener(event -> {
                    String selectedNumber = (String) menuBox.getSelectedItem();
                    if(selectedNumber != null && !selectedNumber.equals(numberHeader)){
                        data.replace("number", selectedNumber);
                        viewController.addSelectedItems(data);
                    }
                    if(isConnect){
                        viewController.action();
                    }
                });
                ProfileType.clickListener(menuBox, viewController);
            }
        };
        // <type, value>
        private static final HashMap<String, String> data = new HashMap<>(3);
        static {
            data.put("assortment", "Тип сортамента");
            data.put("type", "Тип профиля");
            data.put("number", "№ профиля");
        }

        private static void clickListener(MenuBox menuBox, ViewController viewController){
            menuBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if(!isConnect){
                        viewController.setMessage(NOT_DATABASE_MESSAGE, true);
                        viewController.setResult(ERROR, true);
                    }
                }
            });
        }

        private static void createMenuModel(MenuBox menuBox, String header, ViewController viewController, List<String> receivableMenu) {
            if(receivableMenu == null){
                receivableMenu = new LinkedList<>();
            }
            receivableMenu.add(0, header);
            viewController.createMenu(receivableMenu, menuBox);
        }

        abstract void receiveMenu(MenuBox menuBox, DataReceiver dataReceiver, ViewController viewController);
        abstract void addActionListener(MenuBox menuBox, ViewController viewController);
    }
}
