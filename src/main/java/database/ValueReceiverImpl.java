package database;

import model.ValueReceiver;

import java.sql.SQLException;

public class ValueReceiverImpl implements ValueReceiver {

    private final DetailsValue detailsValue;

    public ValueReceiverImpl(Data data) {
        detailsValue = new DetailsValue(data);
    }

    @Override
    public double getValue(String assortment, String type, String number) throws SQLException {
        return detailsValue.getValue(assortment, type, number);
    }
}
