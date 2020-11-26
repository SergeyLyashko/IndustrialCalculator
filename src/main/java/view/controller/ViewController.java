package view.controller;

import java.awt.event.KeyEvent;

public interface ViewController {

    void selectMenu(MenuSelectable menuSelectable, String selectedItem);

    void fieldActivate(FieldSelectable fieldSelectable);

    void fieldDeactivate(FieldSelectable fieldSelectable);

    void fieldFocusGained(FieldSelectable fieldSelectable);

    void keyPressed(KeyEvent event);

    void keyReleased(KeyEvent event);

    Visitor getVisitor();

    void setNotWidthState(MenuSelectable menuSelectable);

    void setWidthState(MenuSelectable menuSelectable);

    void actionState(MenuSelectable menuSelectable);

    void setStateTarget(FieldSelectable fieldSelectable);

    void checkBoxSelect();

    void checkBoxDeselect();
}
