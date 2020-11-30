package model;

import java.sql.SQLException;

public interface ValueReceivable {

    double getValue(String assortment, String type, String number) throws SQLException;
}
