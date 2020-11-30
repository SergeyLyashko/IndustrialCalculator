package database;

import model.ValueReceivable;

import java.sql.SQLException;

public class ValueReceivableImpl implements ValueReceivable {

    private final DetailsValue detailsValue;

    public ValueReceivableImpl(Data data) {
        detailsValue = new DetailsValue(data);
    }

    @Override
    public double getValue(String assortment, String type, String number) throws SQLException {
        return detailsValue.getValue(assortment, type, number);
    }
}
