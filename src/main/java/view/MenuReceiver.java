package view;

import java.util.List;

public interface MenuReceiver {

    List<String> getAssortmentMenu();

    List<String> getTypeMenu(String assortment);

    List<String> getNumberMenu(String type);
}
