package database;

import view.MenuReceiver;

public class DataBaseDispatcher {

    private final Data data;

    public DataBaseDispatcher(){
        data = new Data();
        Connector connector = new Connector();
        data.addConnection(connector);
    }

    public MenuReceiver getMenuReceiver(){
        return new MenuReceiverImpl(data);
    }
}
