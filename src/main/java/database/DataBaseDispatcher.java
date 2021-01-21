package database;

import model.ValueReceiver;
import view.DataBaseMenuReceiver;

public class DataBaseDispatcher {

    private final Executor executor;

    public DataBaseDispatcher(){
        executor = new Executor();
        Connector connector = new Connector();
        executor.addConnection(connector);
    }

    public DataBaseMenuReceiver getMenuReceiver(){
        return new DataBaseMenuReceiverImpl(executor);
    }

    public ValueReceiver getValueReceiver() {
        return new ValueReceiverImpl(executor);
    }
}
