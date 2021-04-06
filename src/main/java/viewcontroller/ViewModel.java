package viewcontroller;

import controller.CalculatorData;
import view.AppComponent;
import view.ViewController;
import javax.swing.*;
import java.util.List;
import java.util.Queue;

public interface ViewModel {

    ComboBoxModel<String> createMenuModel(List<String> receiveMenu);

    LabelBehavior createLabelBehavior(AppComponent component);

    FieldBehavior createFieldBehavior(AppComponent component);

    CalculatorData createData(Queue<String> queueItems, AppComponent width, AppComponent length, ViewController viewController);

    FocusBehavior createFocusBehavior(AppComponent component);

    KeyBehavior createKeyBehavior(AppComponent component);

    Filter getDefaultFilter();

    Filter getDigitalFilter();

    Preference getPreference();

    void setToolTipState(boolean selected);
}
