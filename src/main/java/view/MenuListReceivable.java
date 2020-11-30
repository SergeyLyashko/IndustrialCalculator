package view;

import java.util.List;

public interface MenuListReceivable {

    List<String> getAssortmentMenu();

    List<String> getTypeMenu(String assortment);

    List<String> getNumberMenu(String type);
}
