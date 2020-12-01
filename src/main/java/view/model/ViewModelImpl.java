package view.model;

import view.MenuListReceiver;
import view.controller.*;
import view.model.behavior.FieldBehavior;
import view.model.state.CalculatorFieldState;
import view.view.AppComponent;

import java.util.List;

public class ViewModelImpl {

    private final MenuListReceiver menuListReceiver;
    private final Visitor colorVisitor;
    private final CalculatorFieldState fieldState;
    private final FieldBehavior fieldBehavior;

    public ViewModelImpl(MenuListReceiver menuListReceiver) {
        this.menuListReceiver = menuListReceiver;
        colorVisitor = new ColorChangeVisitor();
        fieldState = new CalculatorFieldState(this);
        fieldBehavior = new FieldBehavior(this);
    }

    public void createMenu(MenuSelectable menuSelectable, String selectedItem) {
        // TODO проверка на null
        List<String> receiveMenuList = menuSelectable.receiveMenu(selectedItem);
        MenuListModel menuListModel = new MenuListModel(menuSelectable, receiveMenuList);
        menuListModel.createMenu();
    }

    public MenuListReceiver getMenuReceiver() {
        return menuListReceiver;
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }
///////////////////////////////////////////////////////////////////////////
    public void setNotWidthState() {
        fieldState.setState(fieldState.getNotWidthState());
        fieldState.selectMenu();
    }

    public void setWidthState() {
        fieldState.setState(fieldState.getHaveWidthState());
        fieldState.selectMenu();
    }

    public void setAllFieldOffState() {
        fieldState.setState(fieldState.getAllFieldOffState());
        fieldState.turnNumbers();
    }

    public void actionState() {
        fieldState.turnNumbers();
    }

    public void setField(AppComponent fieldSelectable) {
        fieldState.setField(fieldSelectable);
    }

    public void checkBoxSelect(boolean state) {
        fieldState.checkBoxSelect(state);
    }
///////////////////////////////////////////////////////////////////////////////////////////////

    public void fieldActivate(AppComponent fieldSelectable) {
        fieldBehavior.fieldActivate(fieldSelectable);
    }

    public void fieldDeactivate(AppComponent fieldSelectable) {
        fieldBehavior.fieldDeactivate(fieldSelectable);
    }

    public void areaActivate(AppComponent fieldSelectable){
        fieldBehavior.areaActivate(fieldSelectable);
    }

    public void createData(AppComponent[] components) {
        CalculatorDataObserver calculatorData = new CalculatorData(components);
        fieldBehavior.add(calculatorData);
        // TODO set model
    }

    public void createAreaData(AppComponent[] components) {
        CalculatorDataObserver calculatorData = new CalculatorData(components);
        calculatorData.setAreaStatus(true);
        fieldBehavior.add(calculatorData);
        // TODO set model
    }
}
