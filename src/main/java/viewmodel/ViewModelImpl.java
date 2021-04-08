package viewmodel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import viewcontroller.*;

import javax.swing.*;
import java.util.List;

@Service("viewModel")
public class ViewModelImpl implements ViewModel, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setToolTipState(boolean selected) {
        ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setEnabled(selected);
    }

    public ComboBoxModel<String> createMenuModel(List<String> menuList) {
        MenuModel menuModel = applicationContext.getBean("menuModel", MenuModel.class);
        menuModel.addMenuList(menuList);
        return menuModel;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
