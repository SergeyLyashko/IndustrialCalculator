package controller.impl;

import controller.CalculatorController;
import controller.ViewController;
import model.KeyActionObserver;
import model.LabelBehavior;
import model.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ui.UiComponent;
import ui.MenuSelectable;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("viewController")
class ViewControllerImpl implements KeyActionObserver, ViewController {

    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;

    @Autowired
    private ViewModel viewModel;
    @Autowired
    private CalculatorController calculatorController;
    @Lazy
    @Autowired
    @Qualifier("messageBehavior")
    private LabelBehavior messageBehavior;
    @Lazy
    @Autowired
    @Qualifier("resultBehavior")
    private LabelBehavior resultBehavior;
    //@Autowired
    //private Preference preference;
    @Autowired
    @Qualifier("lengthAction")
    private FieldsAction lengthAction;
    @Autowired
    @Qualifier("widthAction")
    private FieldsAction widthAction;

    @Override
    public void createMenu(List<String> menuList, MenuSelectable menuSelectable) {
        ComboBoxModel<String> menu = viewModel.createMenuModel(menuList);
        JComboBox<String> comboBox = menuSelectable.getComponentParent();
        comboBox.setModel(menu);
    }

    @Override
    public boolean isArea(){
        return lengthAction.isActionState();
    }

    @Override
    public void setToolTipState(boolean selected) {
        viewModel.setToolTipState(selected);
    }

    @Override
    public boolean isWidth(){
        return widthAction.isActionState();
    }

    private void resetServiceString(){
        resultBehavior.reset();
        messageBehavior.reset();
    }

    @Override
    public void fieldsOff() {
        resetServiceString();
        widthAction.deactivate();
        lengthAction.deactivate();
        widthAction.setState(false);
    }

    @Override
    public void action() {
        resetServiceString();
        lengthAction.activate();
        if(widthAction.isActionState()){
            checkSelectedAreaBox();
        }
    }

    private void checkSelectedAreaBox(){
        if(lengthAction.isActionState()){
            widthAction.deactivate();
            lengthAction.areaActivate();
        }else {
            widthAction.activate();
            lengthAction.areaDeactivate();
        }
    }

    @Override
    public void areaCheckBoxState(boolean selectedState) {
        lengthAction.setState(selectedState);
        action();
    }

    @Override
    public void widthOn() {
        widthAction.setState(true);
    }

    @Override
    public void addSelectedItems(Map<String, String> selectedItems) {
        //calculatorData.add(selectedItems);
    }

    @Override
    public void keyActionUpdate() {
        try {
            calculatorController.calculation(/*calculatorData*/);
        } catch (SQLException exception) {
            setMessage(NOT_DATABASE_MESSAGE, ALERT);
            setResult(ERROR, ALERT);
        }
    }

    @Override
    public void setResult(String result, boolean alert) {
        String value = result;
        if(!alert){
            value = result+" кг";
        }
        resultBehavior.show(value, alert);
    }

    @Override
    public void setMessage(String message, boolean alert) {
        messageBehavior.show(message, alert);
    }

    @Override
    public List<UiComponent> loadComponents() {
        // TODO !!!
        //Preference preference = viewModel.getPreference();
        /*if(preference.isSaved()){
            return preference.loadComponents();
        }*/
        return null;
    }

    @Override
    public void savedPreference(List<UiComponent> components) {
        //Preference preference = viewModel.getPreference();
        // TODO !!!
        //preference.saveComponents(components);
    }
}
