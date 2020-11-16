package view.calculator.menuboxes;

import view.AppComponent;
import view.MenuReceiver;
import view.calculator.MenuSelectable;

public class Menu {

    private final MenuReceiver menuReceiver;
    private MenuSelectable assortmentsMenu;
    private MenuSelectable typesMenu;
    private MenuSelectable numbersMenu;

    public Menu(MenuReceiver menuReceiver){
        this.menuReceiver = menuReceiver;
    }

    public void create(){
        assortmentsMenu = new AssortmentsMenu();
        typesMenu = new TypesMenu();
        numbersMenu = new NumbersMenu();


    }

    public AppComponent createMenu(MenuSelectable menuSelectable){
        MenuModel menuModel = new MenuModel(menuReceiver);
        menuModel.createMenu(menuSelectable);
        menuSelectable.setModel(menuModel);
        return menuSelectable.getMenu();
    }



}
