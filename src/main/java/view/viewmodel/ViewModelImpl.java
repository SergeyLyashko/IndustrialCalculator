package view.viewmodel;

import controller.CalculatorData;
import view.AppComponent;
import view.MenuSelectable;
import view.ViewController;
import view.Visitor;
import view.modelfieldsbehavior.FieldBehaviorImpl;
import viewcontroller.LabelBehavior;

import java.util.List;
import java.util.Queue;

public class ViewModelImpl {

    private final Visitor colorVisitor;

    public ViewModelImpl() {
        colorVisitor = new ColorChangeVisitor();
    }

    public FieldBehaviorImpl getFieldBehavior(AppComponent component) {
        return new FieldBehaviorImpl(component);
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
