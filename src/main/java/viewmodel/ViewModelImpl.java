package viewmodel;

import org.springframework.stereotype.Service;
import view.AppComponent;
import viewcontroller.*;

import javax.swing.*;
import java.util.List;

@Service("viewModel")
public class ViewModelImpl implements ViewModel {

    public FieldBehavior createFieldBehavior(AppComponent component) {
        return new FieldBehaviorImpl(component);
    }

    @Override
    public FocusBehavior createFocusBehavior(AppComponent component) {
        return new FocusBehaviorImpl(component);
    }

    @Override
    public void setToolTipState(boolean selected) {
        ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setEnabled(selected);
    }

    public ComboBoxModel<String> createMenuModel(List<String> menuList) {
        return new MenuListModel(menuList);
    }
}
