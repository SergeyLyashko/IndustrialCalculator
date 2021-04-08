package viewcontroller;

import controller.CalculatorData;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import view.AppComponent;
import view.MenuSelectable;
import view.ViewController;
import javax.swing.*;
import java.util.List;
import java.util.Queue;

@Service("viewController")
public class ViewControllerImpl implements ViewController, ApplicationContextAware {

    private ViewModel viewModel;
    private CalculatorController calculatorController;
    private FieldsAction lengthAction;
    private FieldsAction widthAction;

    private LabelBehavior messageBehavior;
    private LabelBehavior resultBehavior;
    private Queue<String> queueItems;
    private Preference preference;
    private ApplicationContext applicationContext;
    private CalculatorData calculatorData;

    @Autowired
    @Qualifier("widthAction")
    public void setWidthAction(FieldsAction widthAction){
        this.widthAction = widthAction;
    }

    @Autowired
    @Qualifier("lengthAction")
    public void setLengthAction(FieldsAction lengthAction){
        this.lengthAction = lengthAction;
    }

    @Lazy
    @Autowired
    public void setCalculatorData(CalculatorData calculatorData){
        this.calculatorData = calculatorData;
    }

    @Autowired
    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Autowired
    public void setCalculatorController(CalculatorController calculatorController){
        this.calculatorController = calculatorController;
    }

    @Autowired
    public void setPreference(Preference preference){
        this.preference = preference;
    }

    @Override
    public void createMenu(List<String> menuList, MenuSelectable menuSelectable) {
        ComboBoxModel<String> menu = viewModel.createMenuModel(menuList);
        JComboBox<String> comboBox = menuSelectable.getComponentParent();
        comboBox.setModel(menu);
    }

    @Override
    public void setResultComponent(AppComponent component) {
        LabelBehavior labelBehavior = applicationContext.getBean("labelBehavior", LabelBehavior.class);
        labelBehavior.setComponent(component);
        this.resultBehavior = labelBehavior;
    }

    @Override
    public void setMessageComponent(AppComponent component) {
        LabelBehavior labelBehavior = applicationContext.getBean("labelBehavior", LabelBehavior.class);
        labelBehavior.setComponent(component);
        this.messageBehavior = labelBehavior;
    }

    @Override
    public boolean isArea(){
        return lengthAction.isActionState();
    }

    @Override
    public List<AppComponent> loadComponents() {
        //Preference preference = viewModel.getPreference();
        if(preference.isSaved()){
            return preference.loadComponents();
        }
        return null;
    }

    @Override
    public void savedPreference(List<AppComponent> components) {
        //Preference preference = viewModel.getPreference();
        preference.saveComponents(components);
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
    public void setSelectedItems(Queue<String> queueItems) {
        this.queueItems = queueItems;
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
    public void keyActionUpdate() {
        calculatorData.addData(queueItems);
        calculatorController.setCalculatorData(calculatorData);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
