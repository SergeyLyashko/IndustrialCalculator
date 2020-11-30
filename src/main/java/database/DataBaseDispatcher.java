package database;

import model.ValueReceivable;
import view.MenuListReceiver;

public class DataBaseDispatcher {

    private final Data data;

    public DataBaseDispatcher(){
        data = new Data();
        Connector connector = new Connector();
        data.addConnection(connector);
    }

    public MenuListReceiver getMenuReceiver(){
        return new MenuListReceiverImpl(data);
    }

    public ValueReceivable getValueReceiver() {
        return new ValueReceivableImpl(data);
    }
}
