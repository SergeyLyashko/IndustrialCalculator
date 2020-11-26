package view.controller;

import java.awt.event.KeyEvent;

public interface ViewController {

    void selectMenu(MenuSelectable menuSelectable, String selectedItem);

    void fieldActivate(FieldSelectable fieldSelectable);

    void fieldDeactivate(FieldSelectable fieldSelectable);

    void fieldFocusGained(FieldSelectable fieldSelectable);

    void keyPressed(KeyEvent event);

    void keyReleased(KeyEvent event);

    void activateVisitor(Host host);

    void deactivateVisitor(Host host);
}
