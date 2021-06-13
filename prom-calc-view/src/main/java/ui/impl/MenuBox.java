package ui.impl;

import controller.ViewController;
import database.MenuListProducer;
import lombok.Getter;
import model.impl.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.FocusPolicy;
import ui.MenuSelectable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Menu combo boxes for assortment, types, numbers menu
 * Menu model in child menu box created whichever selected point in parent menu box.
 * inheritance menu model builder scheme:  assortment menu selected item -> types menu selected item -> numbers menu
 * @author Sergey Lyashko
 */
@Component
@Scope("prototype")
class MenuBox extends JComboBox<String> implements MenuSelectable, FocusPolicy {

    private static final String NOT_DATABASE_MESSAGE = "Отсутствует соединение с БД";
    private static final String ERROR = "error";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private static boolean isConnect = true;
    private final FocusRate focusRate;

    /**
     * Constructor for menu boxes
     * @param toolTipText tooltips of this menu box
     * @param locationX X-location on panel
     * @param locationY Y-location on panel
     * @param focusRate rate for focus traversal policy on panel
     */
    MenuBox(String toolTipText, int locationX, int locationY, FocusRate focusRate){
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
    public JComboBox<String> getComponentParent() {
        return this;
    }

    @Override
    public int getFocusRate() {
        return focusRate.getRate();
    }

    enum FocusRate{
        FIRST(1),
        SECOND(2),
        THIRD(3);

        @Getter
        private final int rate;

        FocusRate(int rate) {
            this.rate = rate;
        }
    }

    enum ProfileType {

        ASSORTMENTS{
            @Override
            void createMenuBox(Data data, MenuListProducer menuListProducer, ViewController viewController, MenuSelectable... menuBoxes) {
                MenuSelectable assortmentMenuBox = menuBoxes[0];
                MenuSelectable typeMenuBox = menuBoxes[1];
                MenuSelectable numberMenuBox = menuBoxes[2];

                List<String> assortmentMenu = new LinkedList<>();
                try {
                    assortmentMenu = menuListProducer.produceAssortments();
                } catch (SQLException exception) {
                    isConnect = false;
                }
                assortmentMenu.add(0, TYPE_VALUE_DATA.get("assortment"));
                ProfileType.createMenuModel(assortmentMenuBox, viewController, assortmentMenu);
                ProfileType.clickListener(assortmentMenuBox, viewController);
                addActionListener(data, menuListProducer, viewController, assortmentMenuBox, typeMenuBox, numberMenuBox);
            }

            private void addActionListener(Data data, MenuListProducer menuListProducer, ViewController viewController, MenuSelectable assortmentMenuBox, MenuSelectable typeMenuBox, MenuSelectable numberMenuBox) {
                assortmentMenuBox.addActionListener(event -> {
                    viewController.fieldsOff();
                    String selectedAssortment = assortmentMenuBox.getSelectedItem();
                    data.setAssortment(selectedAssortment); // TODO !!!
                    TYPE_VALUE_DATA.replace("assortment", selectedAssortment);
                    TYPE_VALUE_DATA.replace("type", "Тип профиля");
                    TYPE_VALUE_DATA.replace("number", "№ профиля");
                    ProfileType.TYPES.createMenuBox(data, menuListProducer, viewController, typeMenuBox, numberMenuBox);
                    ProfileType.NUMBERS.createMenuBox(data, menuListProducer, viewController, numberMenuBox);
                });
            }
        },

        TYPES{
            @Override
            void createMenuBox(Data data, MenuListProducer menuListProducer, ViewController viewController, MenuSelectable... menuBoxes) {
                MenuSelectable assortmentMenuBox = menuBoxes[0];
                MenuSelectable typeMenuBox = menuBoxes[1];

                List<String> typesMenu = new LinkedList<>();
                try {
                    typesMenu = menuListProducer.produceTypes(TYPE_VALUE_DATA.get("assortment"));
                } catch (SQLException exception) {
                    isConnect = false;
                }
                typesMenu.add(0, "Тип профиля");
                ProfileType.createMenuModel(assortmentMenuBox, viewController, typesMenu);
                ProfileType.clickListener(assortmentMenuBox, viewController);
                addActionListener(data, menuListProducer, viewController, assortmentMenuBox, typeMenuBox);
            }

            private void addActionListener(Data data, MenuListProducer menuListProducer, ViewController viewController, MenuSelectable assortmentMenuBox, MenuSelectable typeMenuBox) {
                assortmentMenuBox.addActionListener(event -> {
                    String selectedType = assortmentMenuBox.getSelectedItem();
                    data.setType(selectedType); // TODO !!!
                    viewController.fieldsOff();
                    if(selectedType.equalsIgnoreCase("резиновая пластина") ||
                            selectedType.equalsIgnoreCase("тонколистовая") ||
                            selectedType.equalsIgnoreCase("толстолистовая") ||
                            selectedType.equalsIgnoreCase("рифленая(ромб)")){
                        viewController.widthOn();
                    }
                    TYPE_VALUE_DATA.replace("type", selectedType);
                    ProfileType.NUMBERS.createMenuBox(data, menuListProducer, viewController, typeMenuBox);
                });
            }
        },

        NUMBERS{
            @Override
            void createMenuBox(Data data, MenuListProducer menuListProducer, ViewController viewController, MenuSelectable... menuBoxes) {
                MenuSelectable assortmentMenuBox = menuBoxes[0];

                List<String> numbersMenu = new LinkedList<>();
                try {
                    numbersMenu = menuListProducer.produceNumbers(TYPE_VALUE_DATA.get("assortment"), TYPE_VALUE_DATA.get("type"));
                } catch (SQLException exception) {
                    isConnect = false;
                }
                numbersMenu.add(0, "№ профиля");
                ProfileType.createMenuModel(assortmentMenuBox, viewController, numbersMenu);
                ProfileType.clickListener(assortmentMenuBox, viewController);
                addActionListener(data, viewController, assortmentMenuBox);
            }

            private void addActionListener(Data data, ViewController viewController, MenuSelectable assortmentMenuBox) {
                String numberHeader = "№ профиля";
                assortmentMenuBox.addActionListener(event -> {
                    String selectedNumber = assortmentMenuBox.getSelectedItem();
                    if(selectedNumber != null && !selectedNumber.equals(numberHeader)){
                        data.setNumber(selectedNumber); // TODO !!!!
                        TYPE_VALUE_DATA.replace("number", selectedNumber);
                        viewController.addSelectedItems(TYPE_VALUE_DATA);
                    }
                    if(isConnect){
                        viewController.action();
                    }
                });
            }
        };

        // <type, value>
        private static final HashMap<String, String> TYPE_VALUE_DATA = new HashMap<>(3);
        static {
            TYPE_VALUE_DATA.put("assortment", "Тип сортамента");
            TYPE_VALUE_DATA.put("type", "Тип профиля");
            TYPE_VALUE_DATA.put("number", "№ профиля");
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

        /**
         * Create menu box from menu list
         * @param menuListProducer database menu list producer
         * @param viewController
         * @param menuBoxes parents menu boxes whichever selected items
         */
        abstract void createMenuBox(Data data, MenuListProducer menuListProducer, ViewController viewController, MenuSelectable... menuBoxes);
    }
}
