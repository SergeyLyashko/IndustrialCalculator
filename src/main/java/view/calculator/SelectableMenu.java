package view.calculator;

import view.AppComponent;
import view.ReceivableMenu;
import view.calculator.menuboxes.MenuModel;

import java.util.List;

public interface SelectableMenu extends AppComponent {

    List<String> receiveMenu(String menuItem);

    String getHeaderMenu();

    void setModel(MenuModel menuModel);

    AppComponent getComponent();

    void addListener(SelectableMenu selectableMenu);

    void addReceiver(ReceivableMenu receivableMenu);
}
