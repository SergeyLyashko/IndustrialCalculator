package view.calculator.menuboxes;

import view.calculator.MenuSelectable;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<String> menuList = new ArrayList<>();

    public void createMenu(MenuSelectable menuSelectable, String menuItem){
        menuList.add(menuSelectable.getHeaderMenu());
        List<String> receiveMenu = menuSelectable.receiveMenu(menuItem);
        if(receiveMenu != null){
            menuList.addAll(receiveMenu);
        }
        MenuModel menuModel = new MenuModel();
        menuModel.createModel(menuList);
        menuSelectable.setModel(menuModel);
    }
}
