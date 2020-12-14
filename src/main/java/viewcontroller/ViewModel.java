package viewcontroller;

import controller.CalculatorData;
import view.AppComponent;
import view.MenuSelectable;
import view.ViewController;
import view.Visitor;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

public interface ViewModel {

    ComboBoxModel<String> createMenu(List<String> receiveMenu);

    LabelBehavior getLabelBehavior(AppComponent component);

    Visitor getVisitor();

    FieldBehavior getFieldBehavior(AppComponent component);

    CalculatorData getData(Queue<String> queueItems, AppComponent width, AppComponent length, ViewController viewController);

    FocusBehavior getFocusBehavior(AppComponent component);

    KeyBehavior getKeyBehavior(AppComponent component);

    Filter getDefaultFilter();

    Filter getDigitalFilter();
}
