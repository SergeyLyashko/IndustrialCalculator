package view.calculator;

import view.AppComponent;
import view.MenuReceivable;
import view.calculator.menuboxes.MenuModel;

import java.util.List;

public interface MenuSelectable extends AppComponent {

    List<String> receiveMenu(String menuItem);

    String getHeaderMenu();

    void setMenuModel(MenuModel menuModel);

    void addReceiver(MenuReceivable menuReceivable);

    void addListener(CalculatorFieldState calculatorFieldState);
}
