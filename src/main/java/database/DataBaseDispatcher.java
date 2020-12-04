package database;

import model.ValueReceiver;
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

    public ValueReceiver getValueReceiver() {
        return new ValueReceiverImpl(data);
    }
}
