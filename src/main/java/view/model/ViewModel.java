package view.model;

import view.controller.MenuReceivable;
import view.controller.MenuSelectable;

import java.util.List;

public class ViewModel implements ViewModelInterface {

    private final MenuReceivable menuReceivable;

    public ViewModel(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
    }

    @Override
    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenu = menuSelectable.receiveMenu(menuReceivable, selectedItem);
        MenuModel menuModel = new MenuModel(menuSelectable, receiveMenu);
        menuSelectable.setMenuModel(menuModel);
    }
}
