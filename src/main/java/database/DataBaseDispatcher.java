package database;

import view.MenuReceivable;

public class DataBaseDispatcher {

    private final Data data;

    public DataBaseDispatcher(){
        data = new Data();
        Connector connector = new Connector();
        data.addConnection(connector);
    }

    public MenuReceivable getMenuReceiver(){
        return new MenuReceivableImpl(data);
    }
}
