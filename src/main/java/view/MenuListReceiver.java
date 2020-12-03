package view;

import java.util.List;

public interface MenuListReceiver {

    List<String> getAssortmentMenu();

    List<String> getTypeMenu(String assortment);

    List<String> getNumberMenu(String assortment, String type);
}
