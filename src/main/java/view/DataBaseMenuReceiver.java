package view;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseMenuReceiver {

    List<String> getAssortmentMenu() throws SQLException;

    List<String> getTypeMenu(String assortment) throws SQLException;

    List<String> getNumberMenu(String assortment, String type) throws SQLException;
}
