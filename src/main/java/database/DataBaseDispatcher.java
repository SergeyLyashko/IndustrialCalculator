package database;

import model.ValueReceiver;
import view.DataBaseMenuReceiver;

public class DataBaseDispatcher {

    private final Data data;

    public DataBaseDispatcher(){
        data = new Data();
        Connector connector = new Connector();
        data.addConnection(connector);
    }

    public DataBaseMenuReceiver getMenuReceiver(){
        return new DataBaseMenuReceiverImpl(data);
    }

    public ValueReceiver getValueReceiver() {
        return new ValueReceiverImpl(data);
    }
}
