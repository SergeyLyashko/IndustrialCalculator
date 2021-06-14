package controller.impl;

import controller.CalculatorController;
import controller.FieldAction;
import controller.ViewController;
import model.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ui.UiComponent;
import ui.MenuSelectable;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

@Service("viewController")
class ViewControllerImpl implements KeyActionObserver, ViewController, ApplicationContextAware {

    private static final String NOT_DATABASE_MESSAGE = "Значение не найдено в БД";
    private static final String ERROR = "error";
    private static final boolean ALERT = true;

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
    private FieldAction lengthAction;
    @Autowired
    @Qualifier("widthAction")
    private FieldAction widthAction;
    @Autowired
    private DataManager dataManager;
    private ApplicationContext applicationContext;

    @Override
    public void createMenu(List<String> menuList, MenuSelectable menuSelectable) {
        //ComboBoxModel<String> menu = viewModel.createMenuModel(menuList);
        MenuModel menuModel = applicationContext.getBean("menuModel", MenuModel.class);
        menuModel.addMenuList(menuList);
        JComboBox<String> comboBox = menuSelectable.getComponent();
        comboBox.setModel(menuModel.getModel());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setToolTipState(boolean selected) {
        //viewModel.setToolTipState(selected);
        ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setEnabled(selected);
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
    }

    @Override
    public void action() {
        resetServiceString();
        lengthAction.activate();
        if(dataManager.haveWidth()){
            checkSelectedAreaBox();
        }
    }

    private void checkSelectedAreaBox(){
        if(dataManager.isAreaOn()){
            widthAction.deactivate();
            lengthAction.areaActivate();
        }else {
            widthAction.activate();
            lengthAction.areaDeactivate();
        }
    }

    @Override
    public void widthCheck() {
        dataManager.checkDataWidth();
    }

    @Override
    public void keyActionUpdate() {
        try {
            calculatorController.calculation();
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
