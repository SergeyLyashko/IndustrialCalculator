package viewmodel;

import controller.CalculatorData;
import view.AppComponent;
import view.ViewController;
import view.Visitor;
import viewcontroller.*;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

public class ViewModelImpl implements ViewModel {

    private final Visitor colorVisitor;
    private final Filter defaultFilter;
    private final Filter digitalFilter;
    private final Preference preference;

    public ViewModelImpl() {
        colorVisitor = new ColorChangeVisitor();
        defaultFilter = new DefaultFilter();
        digitalFilter = new DigitalFilter();
        preference = new PreferenceImpl();
    }

    public FieldBehavior createFieldBehavior(AppComponent component) {
        return new FieldBehaviorImpl(component);
    }

    @Override
    public FocusBehavior createFocusBehavior(AppComponent component) {
        return new FocusBehaviorImpl(component);
    }

    @Override
    public KeyBehavior createKeyBehavior(AppComponent component) {
        return new KeyBehaviorImpl(component);
    }

    @Override
    public Filter getDefaultFilter() {
        return defaultFilter;
    }

    @Override
    public Filter getDigitalFilter() {
        return digitalFilter;
    }

    @Override
    public Preference getPreference() {
        return preference;
    }

    @Override
    public void setToolTipState(boolean selected) {
        ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setEnabled(selected);
    }

    public ComboBoxModel<String> createMenuModel(List<String> menuList) {
        return new MenuListModel(menuList);
    }

    public CalculatorData createData(Queue<String> queueItems, AppComponent width, AppComponent length, ViewController viewController) {
        return new CalculatorDataImpl(queueItems, width, length, viewController);
    }

    public LabelBehavior createLabelBehavior(AppComponent component) {
        return new LabelBehaviorImpl(colorVisitor, component);
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }
}
