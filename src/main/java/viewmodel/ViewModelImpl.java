package viewmodel;

import controller.CalculatorData;
import view.AppComponent;
import view.MenuSelectable;
import view.ViewController;
import view.Visitor;
import viewcontroller.Filter;
import viewcontroller.FocusBehavior;
import viewcontroller.KeyBehavior;
import viewcontroller.LabelBehavior;
import viewcontroller.ViewModel;

import java.util.List;
import java.util.Queue;

public class ViewModelImpl implements ViewModel {

    private final Visitor colorVisitor;
    private final Filter defaultFilter;
    private final Filter digitalFilter;

    public ViewModelImpl() {
        colorVisitor = new ColorChangeVisitor();
        defaultFilter = new DefaultFilter();
        digitalFilter = new DigitalFilter();
    }

    public FieldBehaviorImpl getFieldBehavior(AppComponent component) {
        return new FieldBehaviorImpl(component);
    }

    @Override
    public FocusBehavior getFocusBehavior(AppComponent component) {
        return new FocusBehaviorImpl(component);
    }

    @Override
    public KeyBehavior getKeyBehavior(AppComponent component) {
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

    public void createMenu(List<String> receiveMenu, MenuSelectable menuSelectable) {
        new MenuListModel(receiveMenu, menuSelectable);
    }

    public CalculatorData getData(Queue<String> queueItems, AppComponent width, AppComponent length, ViewController viewController) {
        return new CalculatorDataImpl(queueItems, width, length, viewController);
    }

    public LabelBehavior getLabelBehavior(AppComponent component) {
        return new LabelBehaviorImpl(colorVisitor, component);
    }

    public Visitor getVisitor() {
        return colorVisitor;
    }
}
