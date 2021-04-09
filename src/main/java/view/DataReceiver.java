package view;

import java.sql.SQLException;
import java.util.List;

public interface DataReceiver {

    List<String> receiveAssortmentMenu() throws SQLException;

    List<String> receiveTypeMenu(String assortment) throws SQLException;

    List<String> receiveNumberMenu(String assortment, String type) throws SQLException;

    double getValue(String assortment, String type, String number) throws SQLException;
}
