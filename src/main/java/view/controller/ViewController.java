package view.controller;

import view.view.fields.Width;

import java.awt.event.KeyEvent;

public interface ViewController {

    void selectMenu(MenuSelectable menuSelectable, String selectedItem);

    void fieldActivate(FieldSelectable fieldSelectable);

    void fieldDeactivate(FieldSelectable fieldSelectable);

    void colorThemeSelect();

    void toolTipsSelect();

    void complexAreaBoxSelect();

    void fieldFocusGained(FieldSelectable fieldSelectable);

    void keyPressed(KeyEvent event);

    void keyReleased(KeyEvent event);
}
