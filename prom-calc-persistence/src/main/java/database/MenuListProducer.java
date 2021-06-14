package database;

import java.sql.SQLException;
import java.util.List;

public interface MenuListProducer {

    List<String> produceAssortments() throws SQLException;

    List<String> produceTypes(String assortment) throws SQLException;

    List<String> produceNumbers(String assortment, String type) throws SQLException;

    double produceMenuItemsValue(String assortment, String type, String number) throws SQLException;
}
