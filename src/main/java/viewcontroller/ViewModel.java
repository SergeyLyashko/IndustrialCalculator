package viewcontroller;

import controller.CalculatorData;
import view.AppComponent;
import view.ViewController;
import javax.swing.*;
import java.util.List;
import java.util.Queue;

public interface ViewModel {

    ComboBoxModel<String> createMenuModel(List<String> receiveMenu);

    FieldBehavior createFieldBehavior(AppComponent component);

    CalculatorData createData(Queue<String> queueItems, AppComponent width, AppComponent length, ViewController viewController);

    FocusBehavior createFocusBehavior(AppComponent component);

    void setToolTipState(boolean selected);
}
