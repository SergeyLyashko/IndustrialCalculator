package view.model;

import view.controller.*;

import java.awt.event.KeyEvent;
import java.util.List;

public class ViewModel implements ViewModelInterface {

    private final MenuReceivable menuReceivable;
    private final FieldKeyBehavior fieldKeyBehavior;
    private final FieldFocusBehavior fieldFocusBehavior;
    private final Visitor colorVisitor;

    public ViewModel(MenuReceivable menuReceivable) {
        this.menuReceivable = menuReceivable;
        fieldKeyBehavior = new FieldKeyBehavior();
        fieldFocusBehavior = new FieldFocusBehavior();
        colorVisitor = new ColorVisitorImpl();
    }

    @Override
    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenuList = menuSelectable.receiveMenu(menuReceivable, selectedItem);
        MenuModel menuModel = new MenuModel(menuSelectable, receiveMenuList);
        menuModel.createMenu();
    }

    @Override
    public void fieldActivate(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldActivate(fieldSelectable);
        fieldKeyBehavior.fieldActivate(fieldSelectable);
    }

    @Override
    public void fieldDeactivate(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldDeactivate(fieldSelectable);
        fieldKeyBehavior.fieldDeactivate(fieldSelectable);
    }

    @Override
    public void fieldFocusGained(FieldSelectable fieldSelectable) {
        fieldFocusBehavior.fieldFocusGained(fieldSelectable);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        fieldKeyBehavior.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        fieldKeyBehavior.keyReleased(event);
    }


    @Override
    public Visitor createVisitor() {
        return colorVisitor;
    }
}
