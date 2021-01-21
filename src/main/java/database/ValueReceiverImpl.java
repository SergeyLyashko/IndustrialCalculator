package database;

import model.ValueReceiver;

import java.sql.SQLException;

class ValueReceiverImpl implements ValueReceiver {

    private final DetailsValue detailsValue;

    ValueReceiverImpl(Executor executor) {
        detailsValue = new DetailsValue(executor);
    }

    @Override
    public double getValue(String assortment, String type, String number) throws SQLException {
        return detailsValue.getValue(assortment, type, number);
    }
}
