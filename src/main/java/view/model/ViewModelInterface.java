package view.model;

import view.controller.FieldSelectable;
import view.controller.MenuSelectable;
import view.controller.Visitor;

import java.awt.event.KeyEvent;

public interface ViewModelInterface {

    void createMenu(MenuSelectable menuSelectable, String selectedItem);

    void fieldActivate(FieldSelectable fieldSelectable);

    void fieldDeactivate(FieldSelectable fieldSelectable);

    void fieldFocusGained(FieldSelectable fieldSelectable);

    void keyPressed(KeyEvent event);

    void keyReleased(KeyEvent event);

    Visitor createVisitor();

    void setNotWidthState();

    void setWidthState();

    void actionState();

    void setField(FieldSelectable fieldSelectable);

    void checkBoxSelect(boolean state);
}
