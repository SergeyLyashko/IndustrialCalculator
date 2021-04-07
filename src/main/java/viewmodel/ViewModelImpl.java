package viewmodel;

import controller.CalculatorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.AppComponent;
import view.ViewController;
import view.Visitor;
import viewcontroller.*;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

@Service("viewModel")
public class ViewModelImpl implements ViewModel {

    private Visitor colorVisitor;

    @Autowired
    public void setColorVisitor(Visitor colorVisitor){
        this.colorVisitor = colorVisitor;
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
}
