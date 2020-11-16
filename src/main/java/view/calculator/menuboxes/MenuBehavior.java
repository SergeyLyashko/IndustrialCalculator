package view.calculator.menuboxes;

import view.calculator.MenuSelectable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBehavior implements ActionListener {

    private final MenuSelectable menuSelectable;

    public MenuBehavior(MenuSelectable menuSelectable){
        this.menuSelectable = menuSelectable;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //MenuSelectable source = (MenuSelectable) event.getSource();
        String currentMenu = menuSelectable.getCurrentMenu();

        menuSelectable.actionMenu(currentMenu);
    }
}
