package viewcontroller;

import view.AppComponent;
import javax.swing.*;
import java.util.List;

public interface ViewModel {

    ComboBoxModel<String> createMenuModel(List<String> receiveMenu);

    FieldBehavior createFieldBehavior(AppComponent component);

    FocusBehavior createFocusBehavior(AppComponent component);

    void setToolTipState(boolean selected);
}
