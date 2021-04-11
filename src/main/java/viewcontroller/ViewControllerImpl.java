package viewcontroller;

import controller.CalculatorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import viewcomponents.common.AppComponent;
import viewcomponents.common.MenuSelectable;
import viewcomponents.common.ViewController;
import javax.swing.*;
import java.util.List;
import java.util.Map;

@Service("viewController")
public class ViewControllerImpl implements ViewController {

    private ViewModel viewModel;
    private CalculatorController calculatorController;
    private LabelBehavior messageBehavior;
    private LabelBehavior resultBehavior;
    private Preference preference;
    private CalculatorData calculatorData;

    private FieldsAction lengthAction;
    private FieldsAction widthAction;

    @Autowired
    @Qualifier("messageBehavior")
    public void setMessageBehavior(LabelBehavior messageBehavior){
        this.messageBehavior = messageBehavior;
    }

    @Autowired
    @Qualifier("resultBehavior")
    public void setResultBehavior(LabelBehavior resultBehavior){
        this.resultBehavior = resultBehavior;
    }

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
}
