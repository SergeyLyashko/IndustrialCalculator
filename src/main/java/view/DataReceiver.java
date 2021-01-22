package view;

import java.sql.SQLException;
import java.util.List;

public interface DataReceiver extends WinCloseObserver {

    List<String> getAssortmentMenu() throws SQLException;

    List<String> getTypeMenu(String assortment) throws SQLException;

    List<String> getNumberMenu(String assortment, String type) throws SQLException;

    double getValue(String assortment, String type, String number) throws SQLException;

}
