package view;

import java.sql.SQLException;
import java.util.List;

public interface DataReceiver {

    List<String> createAssortmentMenu() throws SQLException;

    List<String> createTypeMenu(String assortment) throws SQLException;

    List<String> createNumberMenu(String assortment, String type) throws SQLException;

    double getValue(String assortment, String type, String number) throws SQLException;
}
