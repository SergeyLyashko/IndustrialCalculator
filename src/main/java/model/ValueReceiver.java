package model;

import java.sql.SQLException;

public interface ValueReceiver {

    double getValue(String assortment, String type, String number) throws SQLException;
}
