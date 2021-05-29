package controller;

import model.KeyActionObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ui.AppComponent;
import ui.MenuSelectable;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@Service("viewController")
public class ViewControllerImpl implements KeyActionObserver, ViewController {

    @Autowired
    private ViewModel viewModel;
    @Autowired
    private CalculatorController calculatorController;
    @Autowired
    @Qualifier("messageBehavior")
    private LabelBehavior messageBehavior;
    @Autowired
    @Qualifier("resultBehavior")
    private LabelBehavior resultBehavior;
    @Autowired
    private Preference preference;
    @Lazy
    @Autowired
    private CalculatorData calculatorData;
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
        calculatorData.add(selectedItems);
    }

    @Override
    public void keyActionUpdate() {
        calculatorController.calculation(calculatorData);
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
    public List<AppComponent> loadComponents() {
        // TODO !!!
        //Preference preference = viewModel.getPreference();
        /*if(preference.isSaved()){
            return preference.loadComponents();
        }*/
        return null;
    }

    @Override
    public void savedPreference(List<AppComponent> components) {
        //Preference preference = viewModel.getPreference();
        // TODO !!!
        //preference.saveComponents(components);
    }
}
