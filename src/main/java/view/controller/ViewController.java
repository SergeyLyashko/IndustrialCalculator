package view.controller;

import view.MenuListReceivable;

import java.awt.event.KeyEvent;
import java.util.Queue;

public interface ViewController {

    void selectMenu(MenuSelectable menuSelectable, String selectedItem);

    void fieldActivate(FieldSelectable fieldSelectable);

    void fieldDeactivate(FieldSelectable fieldSelectable);

    void fieldFocusGained(FieldSelectable fieldSelectable);

    void keyPressed(KeyEvent event);

    void keyReleased(KeyEvent event);

    Visitor getVisitor();
///////////////////////////////////////////////////////////
    void setAllFieldOffState();

    void setNotWidthState();

    void setWidthState();

    void actionState();

    void setStateTarget(FieldSelectable fieldSelectable);

    void checkBoxSelect(boolean state);
    /////////////////////////////////////////////////////////

    MenuListReceivable getMenuReceiver();


}
