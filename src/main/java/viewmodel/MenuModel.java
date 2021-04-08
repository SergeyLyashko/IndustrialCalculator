package viewmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service("menuModel")
@Scope("prototype")
public class MenuModel extends AbstractListModel<String> implements ComboBoxModel<String> {

    private List<String> receiveMenuList;
    private int selected;

    public void addMenuList(List<String> receiveMenuList){
        this.receiveMenuList = receiveMenuList;
    }

    @Override
    public int getSize() {
        return receiveMenuList.size();
    }

    @Override
    public String getElementAt(int index) {
        return receiveMenuList.get(index);
    }

    @Override
    public void setSelectedItem(Object item) {
        selected = receiveMenuList.indexOf(item);
    }

    @Override
    public Object getSelectedItem() {
        return receiveMenuList.get(selected);
    }
}
