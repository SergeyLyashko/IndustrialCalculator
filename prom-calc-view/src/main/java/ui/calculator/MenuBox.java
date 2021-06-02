package ui.calculator;

import controller.ViewController;
import database.DataReceiver;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.MenuSelectable;
import ui.UiComponent;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope("prototype")
public class MenuBox extends JComboBox<String> implements MenuSelectable, Comparable<UiComponent> {

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
    public String getSelectedItem(){
        return (String) super.getSelectedItem();
    }

    @Override
    public void addActionListener(ActionListener actionListener){
        super.addActionListener(actionListener);
    }

    @Override
    public void addMouseListener(MouseListener mouseListener){
        super.addMouseListener(mouseListener);
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

    public enum ProfileType {

        ASSORTMENTS{
            @Override
            void receiveMenu(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes) {
                List<String> receivableMenu = new LinkedList<>();
                try {
                    receivableMenu = dataReceiver.receiveAssortmentMenu();
                } catch (SQLException exception) {
                    isConnect = false;
                }
                receivableMenu.add(0, data.get("assortment"));
                ProfileType.createMenuModel(menuBoxes[0], viewController, receivableMenu);
                ProfileType.clickListener(menuBoxes[0], viewController);
                addActionListener(dataReceiver, viewController, menuBoxes);
            }

            private void addActionListener(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes) {
                menuBoxes[0].addActionListener(event -> {
                    viewController.fieldsOff();
                    String selectedAssortment = menuBoxes[0].getSelectedItem();
                    data.replace("assortment", selectedAssortment);
                    data.replace("type", "Тип профиля");
                    data.replace("number", "№ профиля");
                    ProfileType.TYPES.receiveMenu(dataReceiver, viewController, menuBoxes[1], menuBoxes[2]);
                    ProfileType.NUMBERS.receiveMenu(dataReceiver, viewController, menuBoxes[2]);
                });
            }
        },

        TYPES{
            @Override
            void receiveMenu(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes) {
                List<String> receivableMenu = new LinkedList<>();
                try {
                    receivableMenu = dataReceiver.receiveTypeMenu(data.get("assortment"));
                } catch (SQLException exception) {
                    isConnect = false;
                }
                receivableMenu.add(0, "Тип профиля");
                ProfileType.createMenuModel(menuBoxes[0], viewController, receivableMenu);
                ProfileType.clickListener(menuBoxes[0], viewController);
                addActionListener(dataReceiver, viewController, menuBoxes);
            }

            private void addActionListener(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes) {
                menuBoxes[0].addActionListener(event -> {
                    String selectedType = menuBoxes[0].getSelectedItem();
                    viewController.fieldsOff();
                    if(selectedType.equalsIgnoreCase("резиновая пластина") ||
                            selectedType.equalsIgnoreCase("тонколистовая") ||
                            selectedType.equalsIgnoreCase("толстолистовая") ||
                            selectedType.equalsIgnoreCase("рифленая(ромб)")){
                        viewController.widthOn();
                    }
                    data.replace("type", selectedType);
                    ProfileType.NUMBERS.receiveMenu(dataReceiver, viewController, menuBoxes[1]);
                });
            }
        },

        NUMBERS{
            @Override
            void receiveMenu(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes) {
                List<String> receivableMenu = new LinkedList<>();
                try {
                    receivableMenu = dataReceiver.receiveNumberMenu(data.get("assortment"), data.get("type"));
                } catch (SQLException exception) {
                    isConnect = false;
                }
                receivableMenu.add(0, "№ профиля");
                ProfileType.createMenuModel(menuBoxes[0], viewController, receivableMenu);
                ProfileType.clickListener(menuBoxes[0], viewController);
                addActionListener(dataReceiver, viewController, menuBoxes);
            }

            private void addActionListener(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes) {
                String numberHeader = "№ профиля";
                menuBoxes[0].addActionListener(event -> {
                    String selectedNumber = menuBoxes[0].getSelectedItem();
                    if(selectedNumber != null && !selectedNumber.equals(numberHeader)){
                        data.replace("number", selectedNumber);
                        viewController.addSelectedItems(data);
                    }
                    if(isConnect){
                        viewController.action();
                    }
                });
            }
        };
        // <type, value>
        private static final HashMap<String, String> data = new HashMap<>(3);
        static {
            data.put("assortment", "Тип сортамента");
            data.put("type", "Тип профиля");
            data.put("number", "№ профиля");
        }

        private static void clickListener(MenuSelectable menuBox, ViewController viewController){
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

        private static void createMenuModel(MenuSelectable menuBox, ViewController viewController, List<String> receivableMenu) {
            if(receivableMenu == null){
                receivableMenu = new LinkedList<>();
            }
            viewController.createMenu(receivableMenu, menuBox);
        }

        abstract void receiveMenu(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes);
        //abstract void addActionListener(DataReceiver dataReceiver, ViewController viewController, MenuSelectable... menuBoxes);
    }
}
