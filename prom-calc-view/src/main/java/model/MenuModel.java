package model;

import javax.swing.*;
import java.util.List;

public interface MenuModel {

    void addMenuList(List<String> menuList);

    ComboBoxModel<String> getModel();

}
