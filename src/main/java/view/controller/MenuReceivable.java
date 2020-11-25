package view.controller;

import java.util.List;

public interface MenuReceivable {

    List<String> getAssortmentMenu();

    List<String> getTypeMenu(String assortment);

    List<String> getNumberMenu(String type);
}
