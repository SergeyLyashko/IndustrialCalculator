package viewmodel;

import org.springframework.stereotype.Service;
import viewcontroller.*;

import javax.swing.*;
import java.util.List;

@Service("viewModel")
public class ViewModelImpl implements ViewModel {

    @Override
    public void setToolTipState(boolean selected) {
        ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setEnabled(selected);
    }

    public ComboBoxModel<String> createMenuModel(List<String> menuList) {
        return new MenuListModel(menuList);
    }
}
